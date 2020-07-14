package com.boss.businesslist.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author LiDaShan
 * @Version 1.0
 * @Date 2020/7/14
 * @Content:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "normal_user")
public class User {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    @Column(name="name")
    private String username;
    private String password;
}
