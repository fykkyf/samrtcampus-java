package com.woniuxy.course.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Nostalgia丶小冯
 * @date 2023/11/28 12:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyExamination {
    private Integer examScheduleId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date examTime;
    private String classRoom;
    private String courseName;
    private String academicYear;
    private String semester;
}
