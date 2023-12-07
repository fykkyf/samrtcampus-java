package com.woniuxy.login.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserVO {
    private Integer uid;
    private String uname;
    private String password;
    private Integer utype;
}
