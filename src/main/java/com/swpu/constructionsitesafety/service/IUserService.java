package com.swpu.constructionsitesafety.service;

import com.swpu.constructionsitesafety.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author saber
 * @since 2024-12-21
 */
public interface IUserService extends IService<User> {
    Integer userLogin(User user);
}
