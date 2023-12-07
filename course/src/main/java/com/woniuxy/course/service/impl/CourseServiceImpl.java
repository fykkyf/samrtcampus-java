package com.woniuxy.course.service.impl;

import com.woniuxy.common.entity.ClassSchedule;
import com.woniuxy.common.entity.Course;
import com.woniuxy.course.mapper.CourseMapper;
import com.woniuxy.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Nostalgia丶
 * @date 2023/11/21 18:45
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseMapper courseMapper;

    @Override
    public List<ClassSchedule> getAllCourseSchedule(Integer sid) {
        return courseMapper.selectAllCourseSchedule(sid);
    }

    @Override
    public List<Course> getAllCourse() {
        return courseMapper.selectAllCourse();
    }
}
