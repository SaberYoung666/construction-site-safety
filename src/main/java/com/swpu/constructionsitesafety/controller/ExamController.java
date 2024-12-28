package com.swpu.constructionsitesafety.controller;

import com.swpu.constructionsitesafety.context.BaseContext;
import com.swpu.constructionsitesafety.entity.Exam;
import com.swpu.constructionsitesafety.entity.Question;
import com.swpu.constructionsitesafety.entity.Record;
import com.swpu.constructionsitesafety.entity.User;
import com.swpu.constructionsitesafety.entity.dto.AnswerExamDTO;
import com.swpu.constructionsitesafety.entity.dto.AnswerQuestionDTO;
import com.swpu.constructionsitesafety.entity.vo.QuestionVO;
import com.swpu.constructionsitesafety.service.IExamService;
import com.swpu.constructionsitesafety.service.IQuestionService;
import com.swpu.constructionsitesafety.service.IRecordService;
import com.swpu.constructionsitesafety.service.IUserService;
import com.swpu.constructionsitesafety.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.swpu.constructionsitesafety.utils.ReturnCode.RC403;

/**
 * <p>
 * 考卷表 前端控制器
 * </p>
 *
 * @author saber
 * @since 2024-12-21
 */
@RestController
@RequestMapping("/exam")
public class ExamController {
	@Autowired
	private IUserService userService;
	@Autowired
	private IExamService examService;
	@Autowired
	private IQuestionService questionService;
	@Autowired
	private IRecordService recordService;

	@GetMapping("/getExam")
	public ResultData<List<QuestionVO>> getExam() {
		User user = userService.getById(BaseContext.getUserId());
		if (user.getAuthority() == 0) {
			return ResultData.fail(RC403.getCode(), RC403.getMessage());
		}
		List<Question> exam = examService.getExam();
		List<QuestionVO> questionVOList = new ArrayList<>();
		StringBuilder questionId = new StringBuilder();
		for (Question question : exam) {
			QuestionVO questionVO = new QuestionVO();
			Integer id = question.getId();
			questionId.append(id).append(",");
			questionVO.setId(id);
			questionVO.setQuestion(question.getContent());
			questionVOList.add(questionVO);
		}
		Exam examEntity = new Exam();
		examEntity.setQuestions(questionId.toString());
		examService.save(examEntity);
		return ResultData.success(questionVOList);
	}

	@PostMapping("/answerExam")
	public ResultData<Double> answerExam(@RequestBody AnswerExamDTO answerExamDTO) {
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
		Record record = new Record();
		record.setExamId(answerExamDTO.getExamId());
		record.setUserId(BaseContext.getUserId());
		record.setScore(points);
		recordService.save(record);
		return ResultData.success(points);
	}
}
