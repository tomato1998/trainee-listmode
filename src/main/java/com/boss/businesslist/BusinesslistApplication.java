package com.boss.businesslist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author li da shan
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "com.boss.businesslist.dao")
public class BusinesslistApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusinesslistApplication.class, args);
    }

}
