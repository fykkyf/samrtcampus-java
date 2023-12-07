package com.woniuxy.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nostalgia丶小冯
 * @date 2023/11/21 20:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//学生课程中间表
public class ClassTimetable {
    private Integer ccid;
    private Integer classId;
    private Integer courseId;
}
