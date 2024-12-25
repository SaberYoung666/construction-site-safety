package com.swpu.constructionsitesafety.controller;

import com.swpu.constructionsitesafety.entity.Question;
import com.swpu.constructionsitesafety.entity.dto.AnswerQuestionDTO;
import com.swpu.constructionsitesafety.entity.vo.AnswerQuestionVO;
import com.swpu.constructionsitesafety.entity.vo.QuestionVO;
import com.swpu.constructionsitesafety.service.IQuestionService;
import com.swpu.constructionsitesafety.utils.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 题目表 前端控制器
 * </p>
 *
 * @author saber
 * @since 2024-12-21
 */
@RestController
@RequestMapping("/question")
@Slf4j
public class QuestionController {
	@Autowired
	IQuestionService questionService;

	@GetMapping("/set")
	public ResultData<Boolean> createQuestions(@RequestParam Integer moduleId) {
		return ResultData.success(questionService.createQuestions(moduleId));
	}

	@PostMapping("/answer")
	public ResultData<AnswerQuestionVO> answerQuestion(@RequestBody AnswerQuestionDTO answerQuestionDTO) {
		AnswerQuestionVO answerQuestionVO = new AnswerQuestionVO();
		String result = questionService.answerQuestions(answerQuestionDTO.getQuestion(), answerQuestionDTO.getAnswer());
		String[] parts = result.split("，", 2);
		answerQuestionVO.setRightOrNot(parts[0].equals("对"));
		answerQuestionVO.setAnalyze(parts[1]);
		return ResultData.success(answerQuestionVO);
	}

	@GetMapping("/getPractice")
	public ResultData<List<QuestionVO>> getPractice(@RequestParam Integer moduleId) {
		List<Question> questions = questionService.getPractice(moduleId);
		List<QuestionVO> questionVOList = new ArrayList<>();
		for (Question question : questions) {
			QuestionVO questionVO = new QuestionVO();
			questionVO.setId(question.getId());
			questionVO.setQuestion(question.getContent());
			questionVOList.add(questionVO);
		}
		return ResultData.success(questionVOList);
	}
}
