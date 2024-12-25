package com.swpu.constructionsitesafety.controller;

import com.swpu.constructionsitesafety.context.BaseContext;
import com.swpu.constructionsitesafety.entity.User;
import com.swpu.constructionsitesafety.entity.dto.LoginDTO;
import com.swpu.constructionsitesafety.entity.dto.UpdatePasswordDTO;
import com.swpu.constructionsitesafety.entity.dto.UpdatePhoneDTO;
import com.swpu.constructionsitesafety.entity.dto.UserIdDTO;
import com.swpu.constructionsitesafety.entity.vo.LoginVO;
import com.swpu.constructionsitesafety.service.IUserService;
import com.swpu.constructionsitesafety.utils.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.swpu.constructionsitesafety.utils.ReturnCode.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author saber
 * @since 2024-12-21
 */

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
	@Autowired
	private IUserService userService;

	@PostMapping("/login")
	public ResultData<LoginVO> login(@RequestBody LoginDTO loginDTO) {
		if (loginDTO.getName() == null || loginDTO.getName().isEmpty()) {
			return ResultData.fail(USERNAME_OR_PASSWORD_ERROR.getCode(), "用户名为空！");
		} else if (loginDTO.getPassword() == null || loginDTO.getPassword().isEmpty()) {
			return ResultData.fail(USERNAME_OR_PASSWORD_ERROR.getCode(), "密码为空！");
		} else {
			LoginVO loginVO = userService.userLogin(loginDTO.getName(), loginDTO.getPassword());
			if (loginVO == null) {
				return ResultData.fail(USERNAME_OR_PASSWORD_ERROR.getCode(), "用户名或密码错误！");
			} else {
				return ResultData.success(loginVO);
			}
		}
	}

	@PostMapping("/updatePassword")
	public ResultData<Boolean> updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO) {
		if (updatePasswordDTO.getNewPassword() == null || updatePasswordDTO.getNewPassword().isEmpty()) {
			return ResultData.fail(USERNAME_OR_PASSWORD_ERROR.getCode(), "用户名为空！");
		} else {
			Integer userId = BaseContext.getUserId();
			Integer i = userService.resetPassword(userId, updatePasswordDTO.getNewPassword());
			if (i == 1) {
				return ResultData.success(true);
			}
			return ResultData.success(false);
		}
	}

	@PostMapping("/resetPassword")
	public ResultData<Boolean> resetPassword(@RequestBody UserIdDTO userIdDTO) {
		User user = userService.getById(BaseContext.getUserId());
		if (user.getAuthority() == 1) {
			Integer i = userService.resetPassword(userIdDTO.getUserId(), "123456");
			if (i == 1) {
				return ResultData.success(true);
			}
			return ResultData.fail(RC501.getCode(), RC501.getMessage());
		}
		return ResultData.fail(RC403.getCode(), RC403.getMessage());
	}

	@PostMapping("/deleteUser")
	public ResultData<Boolean> deleteUser(@RequestBody UserIdDTO userIdDTO) {
		boolean result = userService.removeById(userIdDTO.getUserId());
		return ResultData.success(result);
	}

	@GetMapping("/getInfo")
	public ResultData<User> getInfo() {
		return ResultData.success(userService.getById(BaseContext.getUserId()));
	}

	@GetMapping("/getAllUsersInfo")
	public ResultData<List<User>> getAllUsersInfo() {
		User user = userService.getById(BaseContext.getUserId());
		if (user.getAuthority() == 1) {
			List<User> users = userService.list();
			return ResultData.success(users);
		}
		return ResultData.fail(RC403.getCode(), RC403.getMessage());
	}

	@PostMapping("/updatePhone")
	public ResultData<Boolean> updatePhone(@RequestBody UpdatePhoneDTO updatePhoneDTO) {
		User user = new User();
		user.setId(BaseContext.getUserId());
		user.setPhone(updatePhoneDTO.getNewPhone());
		return ResultData.success(userService.updateById(user));
	}
}
