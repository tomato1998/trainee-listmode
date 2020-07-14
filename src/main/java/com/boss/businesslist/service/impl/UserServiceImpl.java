package com.boss.businesslist.service.impl;

import com.boss.businesslist.dao.UserMapper;
import com.boss.businesslist.entity.PurchaseList;
import com.boss.businesslist.entity.User;
import com.boss.businesslist.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author LiDaShan
 * @Version 1.0
 * @Date 2020/7/14
 * @Content:
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private HttpSession session;
    private final static String DRAFTS="purchaseLists";

    @Override
    public int login(User user) {
        List<User> users = userMapper.select(user);
        System.out.println(users);
        if (!users.isEmpty()){
            if(session.getAttribute(DRAFTS)==null){
                session.setAttribute(DRAFTS,new ConcurrentHashMap<Long, PurchaseList>(16));
            }
            session.setAttribute("user",user);
            log.info("用户:"+user+"已经登录,草稿箱功能已初始化");
            return 1;
        }
        return 0;
    }
}
