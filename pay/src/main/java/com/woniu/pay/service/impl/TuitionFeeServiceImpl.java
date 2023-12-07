package com.woniu.pay.service.impl;

import com.woniu.pay.mapper.TuitionFeeMapper;
import com.woniu.pay.service.TuitionFeeService;
import com.woniuxy.common.entity.Tuition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TuitionFeeServiceImpl implements TuitionFeeService {

    @Autowired
    TuitionFeeMapper tuitionFeeMapper;



//    @Override
//    public List<Tuition> getStudyMoney(Integer studentId) {
//        List<Tuition> getMoney=tuitionFeeMapper.getStudyMoney(studentId);
//        return getMoney;
//    }

    @Override
    public List<Tuition> getAllStudyMoney(Integer studentId) {
        List<Tuition> allStudyMoney = tuitionFeeMapper.getAllStudyMoney(studentId);
        return allStudyMoney;
    }

    @Override
    public List<Tuition> getStudyYear(Integer studentId) {
        List<Tuition> getYear = tuitionFeeMapper.getStudyYear(studentId);
        return getYear;
    }

    @Override
    public void pushStudyMoney(Tuition tuition) {
        tuitionFeeMapper.pushStudyMoney(tuition);
    }
}
