package com.woniuxy.common.entity.DTO;

import cn.hutool.core.date.DateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SkillDTO {
    private Integer id;
    private Integer studentId;
    private String name;
    private Double amount;
    private DateTime time;
    private Integer quantity;
    private Integer status;
}
