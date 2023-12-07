package com.woniuxy.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Bedroom {
    private Integer bedroomId;
    private Integer building;
    private Integer dormitoryNumber;
    private Integer status;
}
