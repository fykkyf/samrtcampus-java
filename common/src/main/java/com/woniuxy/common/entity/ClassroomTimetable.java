package com.woniuxy.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nostalgia丶小冯
 * @date 2023/11/21 17:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//教室课程
public class ClassroomTimetable {
    private Integer id;
    private Integer classroomId;
    private Integer courseId;
    private Integer week;
    private Integer timeSlot;
}
