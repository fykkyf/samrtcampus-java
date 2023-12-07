package com.woniuxy.login.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.woniuxy.common.entity.ResponseResult;
import com.woniuxy.common.util.JwtUtil;
import com.woniuxy.login.pojo.dto.UserDTO;
import com.woniuxy.login.pojo.vo.UserVO;
import com.woniuxy.login.pojo.vo.VerfyCodeVO;
import com.woniuxy.login.service.LoginService;
import com.woniuxy.login.util.SmsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
@Slf4j
@RestController
@RequestMapping("/school")

public class LoginController {
      @Autowired
      LoginService loginService;
      //登录
      @PostMapping("/login")
      public Object login(@RequestBody UserDTO userDTO, HttpServletResponse response){
            System.out.println(userDTO);
            if(!userDTO.getVerify().equals(redisTemplate.opsForValue().get(userDTO.getUuid()))){
                  return new ResponseResult<>(405,"","验证码错误");
            }
            UserVO userVO = loginService.selectByUname(userDTO);
            if (userVO==null){
                  return new ResponseResult<>(404,"","账号不存在");
            }else if (userDTO.getUname().equals(userVO.getUname()) && DigestUtils.md5DigestAsHex(userDTO.getPassword().getBytes()).equals(userVO.getPassword())){
                  String token = JwtUtil.createToken(String.valueOf(userVO.getUid()), userVO.getUname());
                  response.addHeader("token",token);
                  response.setHeader("Access-Control-Expose-Headers","token");
                  redisTemplate.opsForValue().set("token",token);
                  return new ResponseResult<>(200,"","登录成功");
            }else{
                  return new ResponseResult<>(404,"","密码错误");
            }
      }

      //图形验证码
       @Autowired
      RedisTemplate<String,Object> redisTemplate;
      @RequestMapping("/verify")
      public ResponseResult verify() {
            //定义图形验证码的长和宽
            CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100, 4, 20);
            String verifycode = captcha.getCode();
            System.out.println(verifycode);
            String uuid = UUID.randomUUID().toString().replace("-", "");
            System.out.println(uuid);
            redisTemplate.opsForValue().set(uuid,verifycode);
            //写出到浏览器
            try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                  captcha.write(outputStream);
                  String verifycodesrc = Base64.encode(outputStream.toByteArray());
                  return new ResponseResult(200,"ok",new VerfyCodeVO(uuid,verifycodesrc));
            } catch (IOException e) {
                  throw new RuntimeException(e);
            }
      }

      //忘记密码发送验证码
      @Autowired
      RabbitTemplate rabbitTemplate;
      @GetMapping("/phone/{phone}")
      public Object findPwd(@PathVariable("phone") String phone){

            if(phone==null || phone.equals("")){
                  return new ResponseResult(401,"","手机号为空");
            }else{
                  rabbitTemplate.convertAndSend("exchangetopic","phone",phone);
                  return new ResponseResult(200,"","发送成功");
            }

      }

      //忘记密码
      @PostMapping("/updatePassword")
         public Object updatePassword(@RequestBody UserDTO userDTO){
            System.out.println(userDTO);
            Integer studentId = loginService.selectsIdByPhone(userDTO.getPhone());
            if (studentId==null){
                  return new ResponseResult<>(402,"","手机号与系统预留号码不一致");
            }else {
                  System.out.println(redisTemplate.opsForValue().get(userDTO.getPhone()));
                  if (userDTO.getCode().equals(redisTemplate.opsForValue().get(userDTO.getPhone()))){
                        userDTO.setUname(String.valueOf(studentId));
                        userDTO.setPassword1(DigestUtils.md5DigestAsHex(userDTO.getPassword1().getBytes()));
                        loginService.updatePassword(userDTO);
                        return new ResponseResult<>(200,"","修改成功");
                  }else {
                        return new ResponseResult<>(401,"","验证码错误");
                  }
            }

      }
}
