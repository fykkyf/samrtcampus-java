package com.woniuxy.course.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nostalgia丶小冯
 * @date 2023/11/23 15:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EveryCourse {
    private Integer index;
    private String course;
    private String classRoom;
    private String teacher;
}
