package com.woniu.pay.service;

import com.woniuxy.common.entity.Tuition;

import java.util.List;

public interface TuitionFeeService {


//    List<Tuition> getStudyMoney(Integer studentId);

    List<Tuition> getAllStudyMoney(Integer studentId);

    List<Tuition> getStudyYear(Integer studentId);

    void pushStudyMoney(Tuition tuition);
}
