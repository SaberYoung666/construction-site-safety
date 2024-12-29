package com.swpu.constructionsitesafety.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.swpu.constructionsitesafety.entity.Exam;
import com.swpu.constructionsitesafety.entity.Question;
import com.swpu.constructionsitesafety.entity.Record;
import com.swpu.constructionsitesafety.entity.dto.AnswerExamDTO;

import java.util.List;

/**
 * <p>
 * 考卷表 服务类
 * </p>
 *
 * @author saber
 * @since 2024-12-21
 */
public interface IExamService extends IService<Exam> {
	public List<Question> getExam();

	public void checkExam(AnswerExamDTO answerExamDTO, Record record);
}
