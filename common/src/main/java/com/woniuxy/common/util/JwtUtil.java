package com.woniuxy.common.util;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWT;

public class JwtUtil {

    public static final String JWT_KEY = "woniu";
    public static final String JWT_ISSUER = "woniuzs";
    public static final int JWT_EXPIRE_MILLISECOND = 1000*60*10;
    public static final int JWT_EXPIRE_MILLISECOND_REFRESH = 1000*60*30*2;

    //生成token
    public static String createToken(String eid,String ename){
        return JWT
                .create() //生成token
                .setKey(JWT_KEY.getBytes()) //加密密钥信息
                .setIssuer(JWT_ISSUER)   //签发人
                .setIssuedAt(DateTime.now()) //指定签发时间
                .setExpiresAt(DateTime.now().offset(DateField.MILLISECOND,JWT_EXPIRE_MILLISECOND))   //指定失效时间
                .setPayload("eid",eid)
                .setPayload("ename",ename)   //设置载荷，自定义用户信息
                .setPayload("name","looly")
                .sign();
    }

    public static String createRefresh(String eid,String ename){
        return JWT
                .create() //生成token
                .setKey(JWT_KEY.getBytes()) //加密密钥信息
                .setIssuer(JWT_ISSUER)   //签发人
                .setIssuedAt(DateTime.now()) //指定签发时间
                .setExpiresAt(DateTime.now().offset(DateField.MILLISECOND,JWT_EXPIRE_MILLISECOND_REFRESH))   //指定失效时间
                .setPayload("eid",eid)
                .setPayload("ename",ename)   //设置载荷，自定义用户信息
                .setPayload("name","looly")
                .sign();
    }

    //获取指定payload
    public static String getEid(String token){
        return (String) JWT.of(token).getPayload("eid");
    }

    public static String getEname(String token){
        return JWT.of(token).getPayload("ename").toString();
    }

    //验证是否有效
    public static boolean validate(String token){
        return JWT.of(token).setKey("woniu".getBytes()).verify();
    }

    //验证是否过期
    public static boolean isExpire(String token){
        try{
            return !JWT.of(token).setKey("woniu".getBytes()).validate(0);
        }catch (Exception e){
            return true;
        }
    }

}
