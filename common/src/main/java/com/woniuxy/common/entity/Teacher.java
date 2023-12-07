package com.woniuxy.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Nostalgia丶
 * @date 2023/11/21 20:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//教师表
public class Teacher {
    private Integer teacherId;
    private String tname;
    private Integer age;
    private String email;
    private Date birthdate;
    private String teacherIdCard;
    private Date admissionDate;
    private Integer departmentID;
    private String teacherApartment;
}
