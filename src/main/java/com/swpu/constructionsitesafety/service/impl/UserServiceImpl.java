package com.swpu.constructionsitesafety.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swpu.constructionsitesafety.context.BaseContext;
import com.swpu.constructionsitesafety.entity.User;
import com.swpu.constructionsitesafety.entity.vo.LoginVO;
import com.swpu.constructionsitesafety.mapper.UserMapper;
import com.swpu.constructionsitesafety.service.IUserService;
import com.swpu.constructionsitesafety.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author saber
 * @since 2024-12-21
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public LoginVO userLogin(String name, String password) {
		Map<String, Object> claim = new HashMap<>();
		LoginVO loginVO = new LoginVO();
		User result = userMapper.findByNameAndPassword(name, password);
		if (result == null) {
			loginVO = null;
		} else {
			claim.put("USER_ID", result.getId());
			String token = JwtUtil.createJWT(claim);
			loginVO.setToken(token);
			loginVO.setName(result.getName());
			Integer module1 = result.getModule1();
			Integer module2 = result.getModule2();
			Integer module3 = result.getModule3();
			Integer module4 = result.getModule4();
			int total = 4;
			Double progress = (((double) (module1 + module2 + module3 + module4) / total) * 100);
			loginVO.setProgress(progress);
		}
		return loginVO;
	}


	@Override
	public User getInfo() {
		int id = BaseContext.getUserId();
		return userMapper.selectById(id);
	}

	@Override
	public List<User> getAllUsersInfo(Integer pageId) {
		Page<User> page = new Page<>(pageId, 10);
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq("authority", 0);
		IPage<User> userPage = userMapper.selectPage(page, wrapper);
		return userPage.getRecords();
	}

	@Override
	public Integer resetPassword(Integer userId, String newPassword) {
		User updateEntity = new User();
		updateEntity.setId(userId);
		updateEntity.setPassword(newPassword);
		// 调用 updateById 方法
		return userMapper.updateById(updateEntity);
	}


}
