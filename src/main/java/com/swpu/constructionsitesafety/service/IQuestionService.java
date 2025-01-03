package com.swpu.constructionsitesafety.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.swpu.constructionsitesafety.entity.Question;

import java.util.List;

/**
 * <p>
 * 题目表 服务类
 * </p>
 *
 * @author saber
 * @since 2024-12-21
 */
public interface IQuestionService extends IService<Question> {

	public Boolean createQuestions(Integer moduleId);

	public String answerQuestions(Integer questionId, String answer);

	public List<Question> getPractice(Integer moduleId);

}
