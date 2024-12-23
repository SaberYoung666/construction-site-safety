package com.swpu.constructionsitesafety.controller;

import com.swpu.constructionsitesafety.entity.User;
import com.swpu.constructionsitesafety.entity.dto.LoginDTO;
import com.swpu.constructionsitesafety.entity.dto.ResetPasswordDTO;
import com.swpu.constructionsitesafety.entity.vo.LoginVO;
import com.swpu.constructionsitesafety.handler.GlobalExceptionHandler;
import com.swpu.constructionsitesafety.mapper.UserMapper;
import com.swpu.constructionsitesafety.service.IUserService;
import com.swpu.constructionsitesafety.service.impl.UserServiceImpl;
import com.swpu.constructionsitesafety.utils.ResultData;
import com.swpu.constructionsitesafety.utils.ReturnCode;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

import static com.swpu.constructionsitesafety.utils.ReturnCode.USERNAME_OR_PASSWORD_ERROR;

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
    public ResultData login(@RequestBody LoginDTO loginDTO) {
        if (loginDTO.getName() == null || "".equals(loginDTO.getName())) {
            return ResultData.fail(USERNAME_OR_PASSWORD_ERROR.getCode(), "用户名为空！");
        } else if (loginDTO.getPassword() == null || "".equals(loginDTO.getPassword())) {
            return ResultData.fail(USERNAME_OR_PASSWORD_ERROR.getCode(), "密码为空！");
        } else {
            LoginVO loginVO = userService.userLogin(loginDTO.getName(),loginDTO.getPassword());
            if (loginVO == null) {
                return ResultData.fail(USERNAME_OR_PASSWORD_ERROR.getCode(), "用户名或密码错误！");
            } else {
                return ResultData.success(loginVO);
            }
        }
    }

    @PostMapping("/resetPassword")
    public ResultData resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO){
        if (resetPasswordDTO.getNewPassword() == null || "".equals(resetPasswordDTO.getNewPassword())) {
            return ResultData.fail(USERNAME_OR_PASSWORD_ERROR.getCode(), "用户名为空！");
        }
        else {
            userService.resetPassword(resetPasswordDTO);
            return ResultData.success();
        }
    }

}
