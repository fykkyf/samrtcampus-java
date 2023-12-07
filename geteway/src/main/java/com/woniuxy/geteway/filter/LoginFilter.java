package com.woniuxy.geteway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.woniuxy.common.entity.ResponseResult;
import com.woniuxy.common.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class LoginFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("进入LoginFilter");
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        System.out.println(request.getPath().value());
        if (request.getPath().value().contains("/school")) {
            log.info("放行，登录界面的操作");
            //放行
            return chain.filter(exchange);
        }
        //设置响应码
//        response.setStatusCode(HttpStatus.UNAUTHORIZED);
//        return response.setComplete();
        //拦截 自定义响应体
        ResponseResult responseResult = new ResponseResult(401, "ok", "先登录");

        ObjectMapper objectMapper = new ObjectMapper();
        String responsebody = null;
        try {
            responsebody = objectMapper.writeValueAsString(responseResult);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }



        //访问其他路径，验证token是否有效
        String token = request.getHeaders().getFirst("token");
        log.info("老token:"+token);
            if (!StringUtils.isEmpty(token) && JwtUtil.validate(token)) {
                log.info("token有效，检验是否过期");
                if (!JwtUtil.isExpire(token)) {
                    log.info("token没有过期，放行,重新生成token");
                    System.out.println(JwtUtil.getEname(token));
                    System.out.println(JwtUtil.getEid(token));

                    token = JwtUtil.createToken(JwtUtil.getEid(token), JwtUtil.getEname(token));
                    log.info("生成新token："+token);
                    response.getHeaders().add("token", token);
                    response.getHeaders().add("Access-Control-Expose-Headers", "token");
                    return chain.filter(exchange);
                } else {
                    log.info("token过期，拦截");
                    ResponseResult responseResult1 = new ResponseResult(402, "error", "token过期");
                    try {
                        responsebody = objectMapper.writeValueAsString(responseResult1);
                        byte[] bytes = responsebody.getBytes();
                        DataBuffer data = response.bufferFactory().wrap(bytes);
                        return response.writeWith(Mono.just(data));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        // response.getHeaders().add("Content-Type","application/json;charset=uft-8");
        byte[] bytes = responsebody.getBytes();
        DataBuffer data = response.bufferFactory().wrap(bytes);
        return response.writeWith(Mono.just(data));

    }


    @Override
    public int getOrder () {
        return 0;
    }

}