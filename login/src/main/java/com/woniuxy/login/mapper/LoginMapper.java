package com.woniuxy.login.mapper;

import com.woniuxy.common.entity.User;
import com.woniuxy.login.pojo.dto.UserDTO;
import com.woniuxy.login.pojo.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface LoginMapper {
    @Select("select * from user where uname=#{uname}")
    UserVO selectByUname(UserDTO userDTO);

    //修改密码前根据手机号查询出学号
    @Select("select student_id from student where phone_number=#{phone}")
    Integer selectsIdByPhone(String phone);

    //修改密码
    @Update("update user set password = #{password1} where uname=#{uname}")
    void updatePassword(UserDTO userDTO);


}
