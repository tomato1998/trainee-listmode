package com.boss.businesslist.controller;

import com.boss.businesslist.entity.CommonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public @ResponseBody CommonResult exceptionHandle(Exception e){
        return new CommonResult(500,"  O.O  简陋的系统，一不小心就出现了异常  O_O ","定义使用了全局异常处理处理异常信息，接下来是异常信息:"+e);
    }
}
