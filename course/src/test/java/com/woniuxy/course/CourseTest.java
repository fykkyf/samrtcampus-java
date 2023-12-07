package com.woniuxy.course;

import com.woniuxy.common.util.JwtUtil;
import com.woniuxy.course.mapper.CourseMapper;
import com.woniuxy.course.mapper.ExaminationMapper;
import com.woniuxy.course.mapper.SelectElectiveCourseMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * @author Nostalgia丶小冯
 * @date 2023/11/22 17:54
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CourseTest {
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    SelectElectiveCourseMapper selectElectiveCourseMapper;
    @Autowired
    ExaminationMapper examinationMapper;

    @Test
    public void test1(){
        courseMapper.selectList(null);
    }

    @Test
    public void test2(){
        System.out.println(courseMapper.selectAllCourseSchedule(1001));
    }
    @Test
    public void test3(){
        System.out.println(selectElectiveCourseMapper.selectAllElectiveCourse());
    }
    @Test
    public void test4(){
        System.out.println(JwtUtil.createToken("1001", "小杨"));
    }

    @Test
    public void test5(){
        System.out.println(examinationMapper.selectAllExamination());
    }
}
