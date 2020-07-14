package com.boss.businesslist.service;

import com.boss.businesslist.entity.User;

/**
 * @author LiDaShan
 * @Version 1.0
 * @Date 2020/7/14
 * @Content:
 */
public interface UserService {

    /**
     * 用户登陆，校验用户名与密码
     * @return 登录成功返回1
     */
    public int login(User user);
}
