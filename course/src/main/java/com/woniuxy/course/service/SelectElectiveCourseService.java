package com.woniuxy.course.service;

import com.woniuxy.common.entity.AcademicPayment;
import com.woniuxy.common.entity.ElectiveCourse;
import com.woniuxy.course.entity.ElectiveCourseInfo;

import java.util.List;

/**
 * @author Nostalgia丶小冯
 * @date 2023/11/23 19:27
 */
public interface SelectElectiveCourseService {
    List<ElectiveCourse> getAllElectiveCourse();

    List<ElectiveCourse> getElectiveCourse(ElectiveCourseInfo electiveCourseInfo);

    void choiceElectiveCourse(Integer sid,Integer ecid,Integer amount);

    Integer selectAmount(Integer ecid);

    void subtractElectiveCourseQuantity(Integer ecid);

    Integer selectQuantityByEcid(Integer ecid);

    List<Integer> getAllElectiveCourseEcid();

    String getAcademicPayment(Integer sid,Integer ecid);

    int findAcademicPaymentOrder(Integer sid,Integer ecid);

    Integer[] getAllElectiveCoursePay(Integer sid);

    List<AcademicPayment> findAcademicPayment(Integer ecid);
}
