package com.woniu.pay.service.impl;

import com.woniu.pay.mapper.AcademicPaymentMapper;
import com.woniu.pay.service.AcademicPaymentService;
import com.woniuxy.common.entity.AcademicPayment;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademicPaymentServiceImpl implements AcademicPaymentService {
    @Autowired
    AcademicPaymentMapper academicPaymentMapper;
    @Override
    public void generated(AcademicPayment academicPayment) {
        academicPaymentMapper.generated(academicPayment);
    }

    @Override
    public List<AcademicPayment> getAllMoney(Integer studentId) {
        List<AcademicPayment> getMoney=academicPaymentMapper.getAllMoney(studentId);
        return getMoney;
    }

//    @Override
//    public void payMoney(Integer id) {
//        academicPaymentMapper.payMoney(id);
//    }
}
