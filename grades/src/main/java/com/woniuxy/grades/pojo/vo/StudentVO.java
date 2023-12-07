package com.woniuxy.grades.pojo.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentVO {
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date graduationDate;
    private String college;
    private String classID;
    private Integer building;
    private Integer dormitoryNumber;
    private Integer status;
    private Integer confirmationInfor;
    private String name;
}
