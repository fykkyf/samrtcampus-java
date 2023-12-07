package com.woniuxy.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamSchedule {
    private Integer examScheduleId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date examTime;
    private Integer classId;
    private Integer courseId;
    private String academicYear;
    private String semester;
    @TableField(exist = false)
    private Classes classes;
    @TableField(exist = false)
    private Course course;
    @TableField(exist = false)
    private ClassRoom classRoom;
}
