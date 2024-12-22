package com.swpu.constructionsitesafety.controller;

import com.swpu.constructionsitesafety.entity.User;
import com.swpu.constructionsitesafety.mapper.UserMapper;
import com.swpu.constructionsitesafety.service.IUserService;
import com.swpu.constructionsitesafety.service.impl.UserServiceImpl;
import com.swpu.constructionsitesafety.utils.ResultData;
import com.swpu.constructionsitesafety.utils.ReturnCode;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

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
public class UserController {
    @Autowired
    private IUserService userService;

/*
    @PostMapping("/login")
    public ResultData<Boolean> login(@RequestBody User user){

    }
*/

}
