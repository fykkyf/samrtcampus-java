package com.woniuxy.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nostalgia丶小冯
 * @date 2023/11/21 17:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//教室
public class ClassRoom {
    private Integer classroomId;
    private Integer building;
    private Integer numbers;
}
