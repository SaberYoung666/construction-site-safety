package com.swpu.constructionsitesafety.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.swpu.constructionsitesafety.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author saber
 * @since 2024-12-21
 */
public interface UserMapper extends BaseMapper<User> {
	@Select("select * from user where phone = #{phone} and password = #{password} limit 1")
	User findByNameAndPassword(@Param("phone") String phone, @Param("password") String password);
	@Select("select * from user where phone = #{phone} limit 1")
	User getUserPhone(@Param("phone") String phone);
}
