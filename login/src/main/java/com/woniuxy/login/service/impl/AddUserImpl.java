package com.woniuxy.login.service.impl;

import com.woniuxy.common.entity.User;
import com.woniuxy.login.mapper.AddUserMapper;
import com.woniuxy.login.service.AddUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddUserImpl implements AddUserService {
    @Autowired
    AddUserMapper addUserMapper;
    @Override
    public void addUser(User user) {
        addUserMapper.addUser(user);
    }
}
