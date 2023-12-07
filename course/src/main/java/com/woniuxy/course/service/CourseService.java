package com.woniuxy.course.service;

import com.woniuxy.common.entity.ClassSchedule;
import com.woniuxy.common.entity.Course;

import java.util.List;

/**
 * @author Nostalgia丶
 * @date 2023/11/21 18:44
 */
public interface CourseService {
    List<ClassSchedule> getAllCourseSchedule(Integer sid);

    List<Course> getAllCourse();
}
