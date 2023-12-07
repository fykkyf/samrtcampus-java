package com.woniuxy.login.service.impl;

import com.woniuxy.common.entity.User;
import com.woniuxy.login.mapper.LoginMapper;
import com.woniuxy.login.pojo.dto.UserDTO;
import com.woniuxy.login.pojo.vo.UserVO;
import com.woniuxy.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginImpl implements LoginService {
    @Autowired
    LoginMapper loginMapper;
    @Override
    public UserVO selectByUname(UserDTO userDTO) {
        return loginMapper.selectByUname(userDTO);
    }

    @Override
    public void updatePassword(UserDTO userDTO) {
        loginMapper.updatePassword(userDTO);
    }

    @Override
    public Integer selectsIdByPhone(String phone) {
        return loginMapper.selectsIdByPhone(phone);
    }
}
