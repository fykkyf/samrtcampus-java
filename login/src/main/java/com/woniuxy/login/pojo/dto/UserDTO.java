package com.woniuxy.login.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
     private String uname;
     private String password;
     private String password1;
     private String password2;
     private String verify;
    private String uuid;
    private String phone;
    private String code;
}
