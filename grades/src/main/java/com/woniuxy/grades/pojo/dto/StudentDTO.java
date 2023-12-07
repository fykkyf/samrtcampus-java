package com.woniuxy.grades.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDTO {
    private Integer studentId;
    private Date minAcademicYear;
    private Date maxAcademicYear;
    private String academicYear;
    private Integer semester;
}
