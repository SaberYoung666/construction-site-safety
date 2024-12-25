package com.swpu.constructionsitesafety.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.swpu.constructionsitesafety.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author saber
 * @since 2024-12-21
 */
public interface UserMapper extends BaseMapper<User> {
	@Select("select * from user where name = #{name} and password = #{password} limit 1")
	User findByNameAndPassword(@Param("name") String name, @Param("password") String password);
}
