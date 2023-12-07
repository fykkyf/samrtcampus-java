package com.woniuxy.common.entity.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class LivingExpenseDTO {
    private Integer id;
    private String alipayNo;
    private Integer studentId;
    private Integer bedroomId;
    private Integer cardId;
    private String type;
    private Double amount;
    private String payTime;
}
