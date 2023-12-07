package com.woniuxy.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
    private Integer studentId;
    private Integer avatarId;
    private String password;
    private String studentName;
    private Integer gender;
    private Integer age;
    private String phoneNumber;
    private String email;
    private Date birthdate;
    private Date admissionDate;
    private Date graduationDate;
    private String college;
    private String departmentID;
    private String classID;
    private Integer building;
    private Integer dormitoryNumber;
    private Integer status;
    private Classes classes;
    private Integer confirmationInfor;
}
