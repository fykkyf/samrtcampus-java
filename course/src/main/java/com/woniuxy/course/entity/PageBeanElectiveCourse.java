package com.woniuxy.course.entity;

import com.woniuxy.common.entity.ElectiveCourse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Nostalgia丶小冯
 * @date 2023/11/24 10:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBeanElectiveCourse {
    private Integer pageIndex;
    private Integer pageSize;
    private long total;
    private Integer totalPage;
    private List<ElectiveCourse> beanList;
}
