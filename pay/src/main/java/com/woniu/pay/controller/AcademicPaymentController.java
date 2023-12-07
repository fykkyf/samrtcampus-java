package com.woniu.pay.controller;


import cn.hutool.http.server.HttpServerRequest;
import com.woniu.pay.service.AcademicPaymentService;
import com.woniu.pay.service.CourseService;
import com.woniuxy.common.entity.AcademicPayment;
import com.woniuxy.common.entity.Grade;
import com.woniuxy.common.entity.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static cn.hutool.core.date.DateTime.now;

@RestController
@RequestMapping("academicPayment")
public class AcademicPaymentController {

    @Autowired
    AcademicPaymentService academicPaymentService;
    @Autowired
    CourseService courseService;

    @RequestMapping("/reStudy")
    public ResponseResult reStudy(@PathVariable Grade grade){
        //先判断是不是补考,如果是
        if(grade.getGradeStatus()==2){
            //判断补考成绩是否低于六十分，低于六十分，就要进行重修，重修就要缴费
            if (grade.getOverallGrade()<60.0){
                //获取课程id
                Integer courseId = grade.getCourseId();
                //通过课程id查出对应的学分
                Double creditById = courseService.getCreditById(courseId);
                //1学分=100元
                Double amount = creditById*100;
                //往教务缴费表里添加数据
                AcademicPayment academicPayment = new AcademicPayment(null,null,grade.getStudentId(),"重修",null, grade.getCourseId(), amount,now(),null,"未缴费");
                academicPaymentService.generated(academicPayment);
                return new ResponseResult<>(200,"ok","重修低于60分，生成费用");
            }
        }else{
            return null;
        }
        return null;
    }

//    @RequestMapping("/generated")
//    public ResponseResult generated(@RequestBody AcademicPayment academicPayment){
//        academicPaymentService.generated(academicPayment);
//        return new ResponseResult<>(200,"ok","已生成重修费用");
//    }


    @RequestMapping("/get")
    public ResponseResult getAllMoney(HttpServerRequest request){
//        String token = request.getHeader("token");
//        Integer eid = Integer.valueOf(JwtUtil.getEid(token));
        Integer eid=1001;
        Integer studentId=eid;
        List<AcademicPayment> getMoney=academicPaymentService.getAllMoney(studentId);
        System.out.println(getMoney);
        return new ResponseResult<>(200,"ok",getMoney);
    }

    //支付重修费用
//    @RequestMapping("/pay/{id}")
//    public ResponseResult payMoney(@PathVariable Integer id){
//        academicPaymentService.payMoney(id);
//        return new ResponseResult<>(200,"ok","支付成功");
//    }

}
