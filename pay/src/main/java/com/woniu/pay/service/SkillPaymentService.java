package com.woniu.pay.service;

import com.woniuxy.common.entity.Skill;
import com.woniuxy.common.entity.SkillExamPayment;

import java.util.List;

public interface SkillPaymentService {
    List<Skill> getAll();

    Integer getMessage(Integer studentId);

    void add(SkillExamPayment skillExamPayment);

    void reduce(Integer id);

    List<SkillExamPayment> getMoney(Integer studentId);
}
