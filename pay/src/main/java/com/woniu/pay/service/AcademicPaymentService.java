package com.woniu.pay.service;

import com.woniuxy.common.entity.AcademicPayment;

import java.util.List;

public interface AcademicPaymentService {
    void generated(AcademicPayment academicPayment);

    List<AcademicPayment> getAllMoney(Integer studentId);

//    void payMoney(Integer id);
}
