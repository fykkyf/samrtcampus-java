package com.woniuxy.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nostalgia丶小冯
 * @date 2023/11/21 17:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//课程时间表
public class ClassSchedule {
    private Integer csid;
    private Integer week;
    private Integer timeSlot;
    private Integer courseId;
    private Integer classroomId;
    private Integer classId;
    private Integer teacherId;
    @TableField(exist = false)
    private Course course;
    @TableField(exist = false)
    private Student student;
    @TableField(exist = false)
    private ClassRoom classRoom;
    @TableField(exist = false)
    private ClassTimetable classTimetable;
    @TableField(exist = false)
    private Teacher teacher;
    @TableField(exist = false)
    private TeacherTimetable teacherTimetable;
    @TableField(exist = false)
    private Classes classes;
}
