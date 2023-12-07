package com.woniuxy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.woniuxy.mapper")
public class CampusLifeApplication {
	public static void main(String[] args) {
		SpringApplication.run(CampusLifeApplication.class, args);
	}
}
