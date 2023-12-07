package com.woniu.pay.common;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {
//    @Autowired
//    AuthorizationInterceptor authorizationInterceptor;

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") //配置允许跨域的请求
                        .allowedOrigins("*")    //配置允许跨域的域名
                        .allowedHeaders("*")    //配置允许跨域请求携带请求头信息
                        .allowedMethods("*")    //配置允许跨域的请求类型 GET POST DELETE PUT
                        .exposedHeaders("*");
            }



            //拦截器,以及放行
//            @Override
//            public void addInterceptors(InterceptorRegistry registry) {
//                registry.addInterceptor(loginInterceptor)
//                        .addPathPatterns("/**")
//                        .excludePathPatterns("/employee/login","/verifycode","/email/**","/employee/findpwd","/employee/updatepwd");
//                registry.addInterceptor(authorizationInterceptor)
//                        .addPathPatterns("/**")
//                        .excludePathPatterns("/employee/login","/verifycode","/email/**","/employee/findpwd","/employee/updatepwd",
//                                "/menu/**","/employee/findEmail","/role/**",
//                                "/permission/**");
//            }
        };
    }
}
