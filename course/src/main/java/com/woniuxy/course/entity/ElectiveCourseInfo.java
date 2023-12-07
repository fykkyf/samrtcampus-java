package com.woniuxy.course.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Nostalgia丶小冯
 * @date 2023/11/24 15:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElectiveCourseInfo {
    private String keyword;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date timeFrom;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date timeTo;
}
