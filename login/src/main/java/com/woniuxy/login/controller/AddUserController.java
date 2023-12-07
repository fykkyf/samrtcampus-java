package com.woniuxy.login.controller;

import com.woniuxy.common.entity.ResponseResult;
import com.woniuxy.common.entity.User;
import com.woniuxy.login.service.AddUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/add")
public class AddUserController {
    @Autowired
    AddUserService userService;
    @PostMapping("/addUser")
    public Object addUser(@RequestBody User user){
        System.out.println(user);
        user.setPassword(DigestUtils.md5DigestAsHex("123".getBytes()));
        user.setUtype(1);
        System.out.println(user);
        userService.addUser(user);
        return new ResponseResult<>(200,"","添加成功");
    }
}
