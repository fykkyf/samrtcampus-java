package com.woniuxy.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExamApplication {
    private Integer id;
    private Integer studentId;
    private Integer courseId;
    private Integer type;
    private Integer status;
    private Date time;
}
