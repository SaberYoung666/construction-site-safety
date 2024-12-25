package com.swpu.constructionsitesafety.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swpu.constructionsitesafety.constant.ZhipuConstant;
import com.swpu.constructionsitesafety.entity.Question;
import com.swpu.constructionsitesafety.exception.ZhiPuException;
import com.swpu.constructionsitesafety.mapper.ModuleMapper;
import com.swpu.constructionsitesafety.mapper.QuestionMapper;
import com.swpu.constructionsitesafety.service.IQuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 题目表 服务实现类
 * </p>
 *
 * @author saber
 * @since 2024-12-21
 */
@Service
@Slf4j
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {
	@Autowired
	private ModuleMapper moduleMapper;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private QuestionMapper questionMapper;

	private static List<Question> getQuestions(Integer moduleId, String questions) {
		String[] parts = questions.split("}\\{");

		// 创建一个List来存储处理后的字符串
		List<String> questionList = new ArrayList<>();

		// 移除每个部分的大括号，并将结果添加到列表中
		for (String part : parts) {
			String cleanedPart = part.replaceAll("[{}]", "");
			questionList.add(cleanedPart);
		}

		if (questionList.size() < 3) {
			throw new ZhiPuException("调用智谱api生成问题出错");
		}

		List<Question> questionEntityList = new ArrayList<>();
		for (String question : questionList) {
			Question questionEntity = new Question();
			questionEntity.setContent(question);
			questionEntity.setModuleId(moduleId);
			questionEntityList.add(questionEntity);
		}
		return questionEntityList;
	}

	@Override
	public Boolean createQuestions(Integer moduleId) {
		String url = ZhipuConstant.URL;
		String moduleName = moduleMapper.selectById(moduleId).getName();
		String content = String.format(ZhipuConstant.SET_QUESTIONS, moduleName);

		// 设置请求头
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", ZhipuConstant.API_KEY);
		headers.set("Content-Type", "application/json");

		// 设置请求体
		Map<String, Object> body = new HashMap<>();
		body.put("messages", new Object[]{
				Map.of(
						"role", "user",
						"content", content
				)
		});
		body.put("model", ZhipuConstant.MODEL);
		body.put("tools", new Object[]{
				Map.of(
						"type", "retrieval",
						"retrieval", Map.of(
								"knowledge_id", ZhipuConstant.KNOWLEDGE_BASE_ID,
								"prompt_template", ZhipuConstant.SET_QUESTIONS_TEMPLATE
						)
				)
		});

		// 封装请求
		HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

		// 发送请求
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

		log.info(response.getBody());

		// 正则表达式匹配 content 的内容
		Pattern pattern = Pattern.compile("\"content\":\"(.*?)\",\"role\":\"assistant\"");
		Matcher matcher = pattern.matcher(Objects.requireNonNull(response.getBody()));

		if (matcher.find()) {
			// 提取匹配的内容
			String questions = matcher.group(1);
			// 使用 "{}" 作为分隔符来分割字符串
			List<Question> questionEntityList = getQuestions(moduleId, questions);
			return saveBatch(questionEntityList);
		} else {
			log.info("Content not found!");
			return false;
		}
	}

	@Override
	public String answerQuestions(Integer questionId, String answer) {
		String url = ZhipuConstant.URL;
		Question question = getById(questionId);
		String content = String.format(ZhipuConstant.ANSWER_QUESTIONS, question, answer);

		// 设置请求头
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", ZhipuConstant.API_KEY);
		headers.set("Content-Type", "application/json");

		// 设置请求体
		Map<String, Object> body = new HashMap<>();
		body.put("messages", new Object[]{
				Map.of(
						"role", "user",
						"content", content
				)
		});
		body.put("model", ZhipuConstant.MODEL);
		body.put("tools", new Object[]{
				Map.of(
						"type", "retrieval",
						"retrieval", Map.of(
								"knowledge_id", ZhipuConstant.KNOWLEDGE_BASE_ID,
								"prompt_template", ZhipuConstant.ANSWER_QUESTIONS_TEMPLATE
						)
				)
		});

		// 封装请求
		HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

		// 发送请求
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

		log.info(response.getBody());

		// 正则表达式匹配 content 的内容
		Pattern pattern = Pattern.compile("\"content\":\"(.*?)\",\"role\":\"assistant\"");
		Matcher matcher = pattern.matcher(Objects.requireNonNull(response.getBody()));

		if (matcher.find()) {
			// 提取匹配的内容
			return matcher.group(1);
		} else {
			log.info("Content not found!");
			return null;
		}
	}

	@Override
	public List<Question> getPractice(Integer moduleId) {
		return questionMapper.getPractice();
	}


}
