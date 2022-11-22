package com.xjs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xjs.mapper")
public class XjsApplication {

    public static void main(String[] args) {
        SpringApplication.run(XjsApplication.class, args);
    }

}
