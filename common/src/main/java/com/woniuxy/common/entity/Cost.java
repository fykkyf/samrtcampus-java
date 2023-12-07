package com.woniuxy.common.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cost {
    private Integer id;
    private String name;
    private Double price;
}
