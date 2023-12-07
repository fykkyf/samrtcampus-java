package com.woniuxy.grades;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.woniuxy.grades.mapper")
public class GradeApplication {
    public static void main(String[] args) {
        SpringApplication.run(GradeApplication.class,args);
    }
}
