package com.swpu.constructionsitesafety.service.impl;

import com.swpu.constructionsitesafety.entity.User;
import com.swpu.constructionsitesafety.mapper.UserMapper;
import com.swpu.constructionsitesafety.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author saber
 * @since 2024-12-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
