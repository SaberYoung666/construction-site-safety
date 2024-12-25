package com.swpu.constructionsitesafety.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author saber
 * @since 2024-12-21
 */
@Getter
@Setter
public class User implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 用户姓名
	 */
	private String name;

	/**
	 * 用户密码
	 */
	private String password;

	/**
	 * 用户权限(0为工人，1为管理员)
	 */
	private Integer authority;

	/**
	 * 性别(0为男，1为女)
	 */
	private Integer gender;

	/**
	 * 年龄
	 */
	private Integer age;

	/**
	 * 是否完成模块1的学习(0为未学习，1为已学习)
	 */
	@TableField(value = "module_1")
	private Integer module1;

	/**
	 * 是否完成模块2的学习(0为未学习，1为已学习)
	 */
	@TableField(value = "module_2")
	private Integer module2;

	/**
	 * 是否完成模块3的学习(0为未学习，1为已学习)
	 */
	@TableField(value = "module_3")
	private Integer module3;

	/**
	 * 是否完成模块4的学习(0为未学习，1为已学习)
	 */
	@TableField(value = "module_4")
	private Integer module4;

	/**
	 * 电话号码
	 */
	private String phone;
}
