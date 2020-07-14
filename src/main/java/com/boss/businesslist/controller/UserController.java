package com.boss.businesslist.controller;

import com.boss.businesslist.entity.CommonResult;
import com.boss.businesslist.entity.User;
import com.boss.businesslist.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;

/**
 * @author LiDaShan
 * @Version 1.0
 * @Date 2020/7/14
 * @Content:
 */
@RequestMapping("/user")
@Slf4j
@RestController
public class UserController implements Serializable {

    @Resource
    private UserService userService;

    @GetMapping("/login")
    public CommonResult login(User user){
        int result = userService.login(user);
        System.out.println(user);
        if(result==0){
            return new CommonResult(666,"密码错误或用户不存在",null);
        }else {
            return new CommonResult(999,"欢迎回来，"+user.getUsername(),"当前时间:"+new Date());
        }
    }
}
