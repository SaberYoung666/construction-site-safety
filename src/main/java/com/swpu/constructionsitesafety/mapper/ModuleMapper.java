package com.swpu.constructionsitesafety.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.swpu.constructionsitesafety.entity.Module;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * 模块表 Mapper 接口
 * </p>
 *
 * @author saber
 * @since 2024-12-21
 */
public interface ModuleMapper extends BaseMapper<Module> {
	@Select("SELECT * FROM module")
	List<Module> LookModules();

	@Update("UPDATE module SET views = views + 1 WHERE id = #{id};")
	int addViews(@Param("id") Integer id);

	@Select("SELECT COUNT(*) FROM user WHERE module_#{module_id} = 1")
	int studiedNum(@Param("module_id") Integer moduleId);
}
