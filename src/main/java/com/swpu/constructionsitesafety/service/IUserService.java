package com.swpu.constructionsitesafety.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.swpu.constructionsitesafety.entity.User;
import com.swpu.constructionsitesafety.entity.vo.LoginVO;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author saber
 * @since 2024-12-21
 */
public interface IUserService extends IService<User> {
	LoginVO userLogin(String name, String password);

	Integer resetPassword(Integer userId, String newPassword);

	User getInfo();

	IPage<User> getAllUsersInfo(Integer pageId);

	IPage<User> selectUser(String likeName);
}
