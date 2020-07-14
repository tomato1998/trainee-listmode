package com.boss.businesslist.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiDaShan
 * @Version 1.0
 * @Date 2020/7/12
 * @Content:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    private Integer code;
    private String  msg;
    private T       data;

    public CommonResult(Integer code,String msg){
        this(code,msg,null);
    }
}
