package com.woniuxy.login.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VerfyCodeVO {
    private String uuid;
    private String verifycodesrc;
}
