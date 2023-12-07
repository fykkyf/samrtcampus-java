package com.woniuxy.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BedroomMoney {
    private Integer id;
    private Integer bedroomId;
    private Double electricityBalance;
    private Double waterBalance;
}
