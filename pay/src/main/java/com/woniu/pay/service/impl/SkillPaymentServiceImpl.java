package com.woniu.pay.service.impl;

import com.woniu.pay.mapper.SkillPaymentMapper;
import com.woniu.pay.service.SkillPaymentService;
import com.woniuxy.common.entity.Skill;
import com.woniuxy.common.entity.SkillExamPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillPaymentServiceImpl implements SkillPaymentService {
    @Autowired
    SkillPaymentMapper skillPaymentMapper;
    @Override
    public List<Skill> getAll() {
        List<Skill> skills=skillPaymentMapper.getAll();
        return skills;
    }

    @Override
    public Integer getMessage(Integer studentId) {
        Integer count=skillPaymentMapper.getMessage(studentId);
        return count;
    }

    @Override
    public void add(SkillExamPayment skillExamPayment) {
        skillPaymentMapper.add(skillExamPayment);
    }

    @Override
    public void reduce(Integer id) {
        skillPaymentMapper.reduce(id);
    }

    @Override
    public List<SkillExamPayment> getMoney(Integer studentId) {
        List<SkillExamPayment> getMoney=skillPaymentMapper.getMoney(studentId);
        return getMoney;
    }
}
