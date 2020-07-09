package com.boss.businesslist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.boss.businesslist.dao")
public class BusinesslistApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusinesslistApplication.class, args);
    }

}
