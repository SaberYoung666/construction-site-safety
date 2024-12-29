package com.swpu.constructionsitesafety.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swpu.constructionsitesafety.entity.Exam;
import com.swpu.constructionsitesafety.entity.Question;
import com.swpu.constructionsitesafety.entity.Record;
import com.swpu.constructionsitesafety.entity.dto.AnswerExamDTO;
import com.swpu.constructionsitesafety.entity.dto.AnswerQuestionDTO;
import com.swpu.constructionsitesafety.mapper.ExamMapper;
import com.swpu.constructionsitesafety.mapper.QuestionMapper;
import com.swpu.constructionsitesafety.service.IExamService;
import com.swpu.constructionsitesafety.service.IQuestionService;
import com.swpu.constructionsitesafety.service.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 考卷表 服务实现类
 * </p>
 *
 * @author saber
 * @since 2024-12-21
 */
@Service
@EnableAsync
public class ExamServiceImpl extends ServiceImpl<ExamMapper, Exam> implements IExamService {
	@Autowired
	private ExamMapper examMapper;
	@Autowired
	private IQuestionService questionService;
	@Autowired
	private IRecordService recordService;

	@Override
	public List<Question> getExam() {
		return examMapper.getExam();
	}

	@Override
	@Async
	public void checkExam(AnswerExamDTO answerExamDTO, Record record) {
		List<AnswerQuestionDTO> answers = answerExamDTO.getAnswers();
		int amount = answers.size();
		double points = 0.0;
		for (AnswerQuestionDTO answer : answers) {
			String result = questionService.answerQuestions(answer.getQuestionId(), answer.getAnswer());
			String[] parts = result.split("，", 2);
			if (parts[0].equals("对")) {
				points += 100.0 / amount;
			}
		}
		record.setScore(points);
		recordService.updateById(record);
	}
}
