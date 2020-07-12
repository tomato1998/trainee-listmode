package com.boss.businesslist.controller;

import com.boss.businesslist.entity.CommonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author LiDaShan
 * @Version 1.0
 * @Date 2020/7/12
 * @Content: 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(Exception.class)
    public CommonResult exceptionHandle(Exception e){
        return new CommonResult(500,"something wrong",e);
    }
}
