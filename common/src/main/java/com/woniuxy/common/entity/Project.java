package com.woniuxy.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nostalgia丶小冯
 * @date 2023/11/21 20:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    private Integer pid;
    private String projectName;
    private Double amount;
}
