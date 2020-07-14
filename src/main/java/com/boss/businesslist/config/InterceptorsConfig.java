package com.boss.businesslist.config;

import com.boss.businesslist.interceptor.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author LiDaShan
 * @Version 1.0
 * @Date 2020/7/14
 * @Content:
 */
@Configuration
public class InterceptorsConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInterceptor()).addPathPatterns("/**").
                excludePathPatterns("/user/login");
    }
}
