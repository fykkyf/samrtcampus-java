package com.woniuxy.course.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nostalgia丶小冯
 * @date 2023/11/25 9:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcademicPaymentOrder {
    private Integer sid;
    private Integer ecid;
    private Integer amount;
}
