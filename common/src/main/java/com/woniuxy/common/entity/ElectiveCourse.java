package com.woniuxy.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Nostalgia丶小冯
 * @date 2023/11/21 20:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//选修课
public class ElectiveCourse {
    private Integer ecid;
    //选修课名称
    private String electiveCourseName;
    //学分
    private Double credit;
    //周期
    private Integer period;
    private Double amount;
    private Integer quantity;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date endTime;
}
