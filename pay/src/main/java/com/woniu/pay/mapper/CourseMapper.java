package com.woniu.pay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniuxy.common.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CourseMapper extends BaseMapper<Course> {

    @Select("select credit from course where course_id=#{courseId}")
    Double getCreditById(Integer courseId);
}
