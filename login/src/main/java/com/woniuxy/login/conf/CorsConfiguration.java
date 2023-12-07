//package com.woniuxy.login.conf;
//
//
//import com.woniuxy.login.interceptor.LoginInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class CorsConfiguration {
//    @Autowired
//    LoginInterceptor loginInterceptor;
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("*")
//                        .allowedHeaders("*")
//                        .allowedMethods("*");
//
//            }
//            @Override
//            public void addInterceptors(InterceptorRegistry registry){
//                registry.addInterceptor(loginInterceptor)
//                        .addPathPatterns("/**")
//                        .excludePathPatterns("/school/login/**","/school/verify/**","/school/updatePassword/**","/school/phone/**","school/updatePassword");
//
//
//            }
//        };
//
//    }
//
//
//
//}
