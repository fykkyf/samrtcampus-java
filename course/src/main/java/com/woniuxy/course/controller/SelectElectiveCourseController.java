package com.woniuxy.course.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.common.entity.AcademicPayment;
import com.woniuxy.common.entity.ElectiveCourse;
import com.woniuxy.common.entity.ResponseResult;
import com.woniuxy.common.util.JwtUtil;
import com.woniuxy.course.entity.ElectiveCourseInfo;
import com.woniuxy.course.mapper.SelectElectiveCourseMapper;
import com.woniuxy.course.service.AcademicPaymentOrderService;
import com.woniuxy.course.service.SelectElectiveCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Nostalgia丶小冯
 * @date 2023/11/23 19:26
**/

//跨域配置
@CrossOrigin
@RestController
@RequestMapping("/selectelectivecourse")
public class SelectElectiveCourseController {
    @Autowired
    SelectElectiveCourseService selectElectiveCourseService;

    @Autowired
    AcademicPaymentOrderService academicPaymentOrderService;

    @Autowired
    SelectElectiveCourseMapper selectElectiveCourseMapper;

    @GetMapping("/get/all")
    public ResponseResult getAllCourse(@RequestParam(defaultValue = "1") Integer pageIndex, @RequestParam(defaultValue = "10") Integer pageSize){
        Page<ElectiveCourse> electiveCoursePage = new Page<>(pageIndex, pageSize);
        QueryWrapper<ElectiveCourse> queryWrapper = new QueryWrapper<>();
        Page<ElectiveCourse> electiveCoursePage1 = selectElectiveCourseMapper.selectPage(electiveCoursePage, queryWrapper);
        List<Integer> allElectiveCourseEcid = selectElectiveCourseService.getAllElectiveCourseEcid();
        for(Integer id : allElectiveCourseEcid){
            academicPaymentOrderService.init(id);
        }
        return new ResponseResult(200,"success",electiveCoursePage1);
    }

    @PostMapping("/findElectiveCourse/")
    public ResponseResult getElectiveCourse(@RequestParam(defaultValue = "1") Integer pageIndex, @RequestParam(defaultValue = "10") Integer pageSize, @RequestBody ElectiveCourseInfo electiveCourseInfo) {
        // 创建分页对象
        Page<ElectiveCourse> electiveCoursePage = new Page<>(pageIndex, pageSize);

        // 创建查询条件包装器
        QueryWrapper<ElectiveCourse> queryWrapper = new QueryWrapper<>();

        // 添加查询条件
        queryWrapper.lambda()
                .ge(ElectiveCourse::getQuantity, 1)
                .and(wrapper -> wrapper
                        .like(ElectiveCourse::getElectiveCourseName, electiveCourseInfo.getKeyword()).or()
                        .between(ElectiveCourse::getStartTime, electiveCourseInfo.getTimeFrom(), electiveCourseInfo.getTimeTo()));

        // 执行查询操作
        Page<ElectiveCourse> electiveCoursePage1 = selectElectiveCourseMapper.selectPage(electiveCoursePage, queryWrapper);

        // 返回结果
        return new ResponseResult(200, "success", electiveCoursePage1);
    }






    @PostMapping("/choiceElectiveCourse/{ecid}")
    public ResponseResult choiceElectiveCourse(@PathVariable("ecid") Integer ecid, HttpServletRequest request) throws Exception {
        String token = request.getHeader("token");
        Integer sid = Integer.valueOf(JwtUtil.getEid(token));
        System.out.println("消费者1收到消息：" + ecid);
        int sum = selectElectiveCourseService.findAcademicPaymentOrder(sid,ecid);
        Integer code = null;
        if (sum < 1){
            code = academicPaymentOrderService.send(ecid, sid);
        }else {
            return new ResponseResult(400,"error",null);
        }
        return new ResponseResult(code,"success",null);
    }

    //查询所有选修课抢到的未付费的课程
    @GetMapping("/get/allElectiveCoursePay/")
    public ResponseResult getAllElectiveCoursePay(HttpServletRequest request){
        String token = request.getHeader("token");
        Integer sid = Integer.valueOf(JwtUtil.getEid(token));
        //Integer[] ecid = selectElectiveCourseService.getAllElectiveCoursePay(sid);
        List<AcademicPayment> academicPayments = selectElectiveCourseService.findAcademicPayment(sid);
        return new ResponseResult(200,"success",academicPayments);
    }


}
