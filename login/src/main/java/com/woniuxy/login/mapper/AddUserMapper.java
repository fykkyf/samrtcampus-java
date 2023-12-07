package com.woniuxy.login.mapper;

import com.woniuxy.common.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddUserMapper {
    //新增用户
    @Insert("INSERT INTO user values (null,#{uname},#{password},utype)")
    void addUser(User user);
}
