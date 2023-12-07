package com.woniuxy.course.service;

/**
 * @author Nostalgia丶小冯
 * @date 2023/11/25 10:15
 */
public interface AcademicPaymentOrderService {
    public void init(Integer ecid);
    public Integer send(Integer ecid,Integer sid);
}
