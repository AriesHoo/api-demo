package com.aries.api.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @Author: AriesHoo on 2019/2/14 10:12
 * @E-Mail: AriesHoo@126.com
 * @Function: 程序入口
 * @Description:
 */
@SpringBootApplication
@MapperScan("com.aries.api.demo.mapper")
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}

