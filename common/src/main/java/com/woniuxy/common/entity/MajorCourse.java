package com.woniuxy.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MajorCourse {
    private Integer id;
    private Integer majorId;
    private Integer courseId;
}
