package com.woniuxy.grades.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SchoolRollDTO {
    private Integer studentId;
    private Integer type;
    private Date minSubmissionTime;
    private Date maxSubmissionTime;
}
