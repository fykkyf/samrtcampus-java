package com.woniuxy.grades.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GradeVO {
    private Integer id;
    private Integer studentId;
    private Integer classId;
    private Integer courseId;
    private Double gradePoint;
    private Date examTime;
    private String academicYear;
    private Integer  semester;
    private Integer gradeStatus;
    private Integer regularGrade;
    private Integer finalGrade;
    private Double overallGrade;
    private String courseName;
}
