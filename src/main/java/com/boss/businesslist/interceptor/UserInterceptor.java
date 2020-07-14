package com.boss.businesslist.interceptor;

import com.boss.businesslist.entity.CommonResult;
import com.boss.businesslist.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author LiDaShan
 * @Version 1.0
 * @Date 2020/7/14
 * @Content:
 */
public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            response.setContentType("application/json; charset=utf-8");
            CommonResult commonResult = new CommonResult(401,"未登录","postman请以get方式直接输入  localhost/user/login?username=tomato&password=123456  进行登录");
            ObjectMapper mapper = new ObjectMapper();
            String result=mapper.writeValueAsString(commonResult);
            response.getWriter().write(result);
            return false;
        }else {
            return true;
        }
    }

}
