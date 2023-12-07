package com.woniuxy.course.service.impl;

import com.woniuxy.common.entity.AcademicPayment;
import com.woniuxy.common.entity.ElectiveCourse;
import com.woniuxy.course.entity.ElectiveCourseInfo;
import com.woniuxy.course.mapper.SelectElectiveCourseMapper;
import com.woniuxy.course.service.SelectElectiveCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Nostalgia丶小冯
 * @date 2023/11/23 19:27
**/

@Service
public class SelectElectiveCourseServiceImpl implements SelectElectiveCourseService {
    @Autowired
    SelectElectiveCourseMapper selectElectiveCourseMapper;
    @Override
    public List<ElectiveCourse> getAllElectiveCourse() {
        return selectElectiveCourseMapper.selectAllElectiveCourse();
    }

    @Override
    public List<ElectiveCourse> getElectiveCourse(ElectiveCourseInfo electiveCourseInfo) {
        return selectElectiveCourseMapper.selectElectiveCourse(electiveCourseInfo);
    }

    @Override
    public void choiceElectiveCourse(Integer sid,Integer ecid,Integer amount) {
        selectElectiveCourseMapper.choiceElectiveCourse(sid,ecid,amount);
    }

    @Override
    public Integer selectAmount(Integer ecid) {
        return selectElectiveCourseMapper.selectAmountByEcid(ecid);
    }

    @Override
    public void subtractElectiveCourseQuantity(Integer ecid) {
        selectElectiveCourseMapper.updateElectiveCourseQuantity(ecid);
    }

    @Override
    public Integer selectQuantityByEcid(Integer ecid) {
        return selectElectiveCourseMapper.selectQuantityByEcid(ecid);
    }

    @Override
    public List<Integer> getAllElectiveCourseEcid() {
        return selectElectiveCourseMapper.selectAllElectiveCourseEcid();
    }

    @Override
    public String getAcademicPayment(Integer sid,Integer ecid) {
        return selectElectiveCourseMapper.getAcademicPayment(sid,ecid);
    }

    @Override
    public int findAcademicPaymentOrder(Integer sid, Integer ecid) {
        return selectElectiveCourseMapper.findAcademicPaymentOrder(sid,ecid);
    }

    @Override
    public Integer[] getAllElectiveCoursePay(Integer sid) {
        return selectElectiveCourseMapper.selectAllElectiveCoursePay(sid);
    }

    @Override
    public List<AcademicPayment> findAcademicPayment(Integer sid) {
        return selectElectiveCourseMapper.selectAcademicPayment(sid);
    }

}
