package com.woniu.pay.service.impl;

import com.woniu.pay.mapper.CourseMapper;
import com.woniu.pay.service.CourseService;
import com.woniuxy.common.entity.Tuition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseMapper courseMapper;

    @Override
    public Double getCreditById(Integer courseId) {
        Double creditById = courseMapper.getCreditById(courseId);
        return creditById;
    }
}
