package com.swpu.constructionsitesafety.mapper;

import com.swpu.constructionsitesafety.entity.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 题目表 Mapper 接口
 * </p>
 *
 * @author saber
 * @since 2024-12-21
 */
public interface QuestionMapper extends BaseMapper<Question> {

	@Select("SELECT * FROM question WHERE module_id = 1 ORDER BY RAND() LIMIT 5;")
	public List<Question> getPractice();

}
