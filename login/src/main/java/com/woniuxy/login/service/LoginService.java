package com.woniuxy.login.service;

import com.woniuxy.common.entity.User;
import com.woniuxy.login.pojo.dto.UserDTO;
import com.woniuxy.login.pojo.vo.UserVO;
import org.apache.ibatis.annotations.Select;

public interface LoginService {
    UserVO selectByUname(UserDTO userDTO);

    void updatePassword(UserDTO userDTO);

    //修改密码前根据手机号查询出学号
    Integer selectsIdByPhone(String phone);
}
