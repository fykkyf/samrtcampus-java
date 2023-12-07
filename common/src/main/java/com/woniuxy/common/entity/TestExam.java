package com.woniuxy.common.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestExam {
    private Integer testId;
    private Integer studentId;
    private String studentName;
    private Integer age;
    private Integer gender;
    private String phoneNumber;
    private Integer skillId;
    private String skillName;
    @JsonFormat(pattern = "yyyy年MM月dd日 HH时mm分ss秒", timezone = "GMT+8")
    private Date skillTime;
    private String skillPlace;
}
