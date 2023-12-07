package com.woniuxy.course.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Nostalgia丶小冯
 * @date 2023/11/23 15:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OneDayCourse {
    private Integer week;
    private List<EveryCourse> courses;
}
