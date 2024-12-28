package com.swpu.constructionsitesafety.service.impl;

import com.swpu.constructionsitesafety.entity.Exam;
import com.swpu.constructionsitesafety.entity.Question;
import com.swpu.constructionsitesafety.mapper.ExamMapper;
import com.swpu.constructionsitesafety.service.IExamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ExamServiceImpl extends ServiceImpl<ExamMapper, Exam> implements IExamService {
	@Autowired
	private ExamMapper examMapper;

	@Override
	public List<Question> getExam() {
		return examMapper.getExam();
	}
}
