package com.woniuxy.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nostalgia丶小冯
 * @date 2023/11/21 17:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//班级表
public class Classes {
    private Integer classId;
    private Integer majorId;
    private Integer numberOfStudent;
}
