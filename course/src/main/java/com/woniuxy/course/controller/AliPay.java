package com.woniuxy.course.controller;

import lombok.Data;

/**
 * @author Nostalgia丶小冯
 * @date 2023/11/29 11:26
 */
@Data
public class AliPay {
    private String traceNo;
    private double totalAmount;
    private String subject;
    private String alipayTraceNo;
}
