package com.swpu.constructionsitesafety.mapper;

import com.swpu.constructionsitesafety.entity.Exam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.swpu.constructionsitesafety.entity.Question;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 考卷表 Mapper 接口
 * </p>
 *
 * @author saber
 * @since 2024-12-21
 */
public interface ExamMapper extends BaseMapper<Exam> {
	@Select("SELECT * FROM question ORDER BY RAND() LIMIT 25;")
	public List<Question> getExam();
}
