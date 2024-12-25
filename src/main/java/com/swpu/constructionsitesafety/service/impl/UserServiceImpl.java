package com.swpu.constructionsitesafety.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swpu.constructionsitesafety.context.BaseContext;
import com.swpu.constructionsitesafety.entity.User;
import com.swpu.constructionsitesafety.entity.dto.ResetPasswordDTO;
import com.swpu.constructionsitesafety.entity.vo.LoginVO;
import com.swpu.constructionsitesafety.mapper.UserMapper;
import com.swpu.constructionsitesafety.service.IUserService;
import com.swpu.constructionsitesafety.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    public Integer resetPassword(ResetPasswordDTO resetPasswordDTO) {
        int id = BaseContext.getUserId();
        log.info(String.valueOf(id));
        User updateEntity = new User();
        updateEntity.setId(id);
        updateEntity.setPassword(resetPasswordDTO.getNewPassword());
        return userMapper.updateById(updateEntity); // 调用 updateById 方法
    }

    @Override
    public User getInfo() {
        int id = BaseContext.getUserId();
        User result = new User();
        result = userMapper.selectById(id);
        return result;
    }
}
