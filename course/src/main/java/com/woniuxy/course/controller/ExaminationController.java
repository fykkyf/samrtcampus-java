package com.woniuxy.course.controller;

import com.woniuxy.common.entity.Course;
import com.woniuxy.common.entity.ExamApplication;
import com.woniuxy.common.entity.ExamSchedule;
import com.woniuxy.common.entity.ResponseResult;
import com.woniuxy.common.util.JwtUtil;
import com.woniuxy.course.entity.MyExamination;
import com.woniuxy.course.service.CourseService;
import com.woniuxy.course.service.ExaminationService;
import com.woniuxy.course.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nostalgia丶小冯
 * @date 2023/11/27 14:39
 */
@CrossOrigin
@RestController
@RequestMapping("/examination")
public class ExaminationController {
    @Autowired
    ExaminationService examinationService;

    @Autowired
    CourseService courseService;


    @GetMapping("/get/all")
    public ResponseResult getAllExamination(){
        List<ExamSchedule> allExamination = examinationService.getAllExamination();
        List<MyExamination> myExaminations = new ArrayList<>();
        for (ExamSchedule e:allExamination){
            myExaminations.add(new MyExamination(e.getExamScheduleId(),e.getExamTime(),e.getClassRoom().getBuilding() + "教" + e.getClassRoom().getNumbers(),
                    e.getCourse().getCourseName(), e.getAcademicYear(),e.getSemester()));
        }
        return new ResponseResult(200,"success",myExaminations);
    }

    @GetMapping("/getAllCourse")
    public ResponseResult getAllCourse(){
        List<Course> allCourse = courseService.getAllCourse();
        return new ResponseResult(200,"success",allCourse);
    }

    @PostMapping("/addExamApplication")
    public ResponseResult addExamApplication(@RequestBody ExamApplication examApplication,HttpServletRequest request){
        System.out.println("==========================="+ examApplication);
        String token = request.getHeader("token");
        Integer sid = Integer.valueOf(JwtUtil.getEid(token));
        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(10l);
        long id = snowflakeIdWorker.nextId();
        examinationService.addExamApplication(sid,id,examApplication);
        return new ResponseResult(200,"success",null);
    }


}
