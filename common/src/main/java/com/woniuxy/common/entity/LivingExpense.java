package com.woniuxy.common.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class LivingExpense {
    private Integer id;
    private String alipayNo;
    private Integer studentId;
    private Integer bedroomId;
    private Integer cardId;
    private String type;
    private Double amount;
    private String payTime;
}
