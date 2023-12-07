package com.woniuxy.course.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniuxy.common.entity.ClassSchedule;
import com.woniuxy.common.entity.Course;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Nostalgia丶小冯
 * @date 2023/11/22 17:53
 */
public interface CourseMapper extends BaseMapper<ClassSchedule> {
    List<ClassSchedule> selectAllCourseSchedule(Integer sid);

    @Select("select * from course")
    List<Course> selectAllCourse();
}
