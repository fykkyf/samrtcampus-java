package com.woniuxy.login.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.woniuxy.common.entity.ResponseResult;
import com.woniuxy.common.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
         log.info("Login");
        log.info(request.getRequestURI());
         if (request.getMethod().equals("OPTIONS")){
               log.info("OPTIONS请求放行");
               return true;
         }
         response.setContentType("text/html;charset=utf-8");
         String token = request.getHeader("token");
             if(!StringUtils.isEmpty(token) && JwtUtil.validate(token)){
                 log.info("token有效，检验是否过期");
                 if(!JwtUtil.isExpire(token)){
                     log.info("token没有过期，放行,重新生成token");
                     System.out.println(JwtUtil.getEname(token));
                     System.out.println(JwtUtil.getEid(token));

//                     token=JwtUtil.createToken((JwtUtil.getEid(token)),JwtUtil.getEname(token));
//                     response.setHeader("token",token);
//                     response.setHeader("Access-Control-Expose-Headers","token");
                     return true;
                 }else {
                     log.info("token过期，拦截");
                     ResponseResult responseResult = new ResponseResult(401,"error","token过期");
                     response.getWriter().write(new ObjectMapper().writeValueAsString(responseResult));
                     return false;
                 }
             }else{
                 log.info("token无效，拦截");

                 response.setContentType("text/html;charset=UTF-8");
                 ResponseResult responseResult = new ResponseResult(401,"error","未登录");
                 response.getWriter().write(new ObjectMapper().writeValueAsString(responseResult));
                 return false;
             }
    }

}
