package com.woniuxy.course.controller;

import com.woniuxy.common.entity.ClassSchedule;
import com.woniuxy.common.entity.ResponseResult;
import com.woniuxy.common.util.JwtUtil;
import com.woniuxy.course.entity.EveryCourse;
import com.woniuxy.course.entity.OneDayCourse;
import com.woniuxy.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Nostalgia丶
 * @date 2023/11/21 19:10
 */
@CrossOrigin
@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    @GetMapping("/get/all")
    public ResponseResult getAllCourseSchedule(HttpServletRequest request){
//        String token = request.getHeader("token");
//        Integer sid = Integer.valueOf(JwtUtil.getEid(token));
        Integer sid = 1001;
        //星期几
        List<OneDayCourse> oneDayCourses = new ArrayList<>();
        List<ClassSchedule> allCourseSchedule = courseService.getAllCourseSchedule(sid);
        for (int i = 1;i <=7;i++){
            //每一天各个时段的课程
            List<EveryCourse> everyCourses = new ArrayList<>();
            for(ClassSchedule c : allCourseSchedule){
            if (c.getWeek()==i){
                everyCourses.add(new EveryCourse(c.getTimeSlot(),c.getCourse().getCourseName(),
                        c.getClassRoom().getBuilding() +"教"+ c.getClassRoom().getNumbers() + "",
                        c.getTeacher().getTname()));
                }
            }
            oneDayCourses.add(new OneDayCourse(i - 1,everyCourses));
        }
        return new ResponseResult(200,"success",oneDayCourses);
    }


}
