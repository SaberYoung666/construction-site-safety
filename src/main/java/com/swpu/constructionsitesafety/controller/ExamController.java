package com.swpu.constructionsitesafety.controller;

import com.swpu.constructionsitesafety.context.BaseContext;
import com.swpu.constructionsitesafety.entity.Exam;
import com.swpu.constructionsitesafety.entity.Question;
import com.swpu.constructionsitesafety.entity.Record;
import com.swpu.constructionsitesafety.entity.dto.AnswerExamDTO;
import com.swpu.constructionsitesafety.entity.dto.AnswerQuestionDTO;
import com.swpu.constructionsitesafety.entity.vo.GetExamVO;
import com.swpu.constructionsitesafety.entity.vo.QuestionVO;
import com.swpu.constructionsitesafety.service.IExamService;
import com.swpu.constructionsitesafety.service.IQuestionService;
import com.swpu.constructionsitesafety.service.IRecordService;
import com.swpu.constructionsitesafety.utils.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 考卷表 前端控制器
 * </p>
 *
 * @author saber
 * @since 2024-12-21
 */
@Slf4j
@RestController
@RequestMapping("/exam")
public class ExamController {
	@Autowired
	private IExamService examService;
	@Autowired
	private IQuestionService questionService;
	@Autowired
	private IRecordService recordService;

	@GetMapping("/getExam")
	public ResultData<GetExamVO> getExam() {
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
		GetExamVO getExamVO = new GetExamVO();
		getExamVO.setExamId(examEntity.getId());
		getExamVO.setQuestions(questionVOList);
		return ResultData.success(getExamVO);
	}

	@PostMapping("/answerExam")
	public ResultData<Double> answerExam(@RequestBody AnswerExamDTO answerExamDTO) {
		Integer examId = answerExamDTO.getExamId();
		Integer userId = BaseContext.getUserId();
		Record record = new Record();
		record.setExamId(examId);
		record.setUserId(userId);
		record.setTime(String.valueOf(LocalDateTime.now()));
		recordService.save(record);
		examService.checkExam(answerExamDTO, record);
		log.info("异步方法调用成功");
		return ResultData.success();
	}
}
