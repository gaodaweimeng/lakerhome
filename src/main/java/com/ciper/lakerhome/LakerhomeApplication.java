package com.ciper.lakerhome;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.ciper.lakerhome.mapper")
@SpringBootApplication
public class LakerhomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(LakerhomeApplication.class, args);
        System.out.println("hello world!");
    }

}
