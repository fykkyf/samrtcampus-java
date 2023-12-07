package com.woniuxy.course;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Nostalgia丶小冯
 * @date 2023/11/22 17:49
 */
@SpringBootApplication
@MapperScan("com.woniuxy.course.mapper")
public class CourseSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(CourseSpringBootApplication.class,args);
    }
}