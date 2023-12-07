package com.woniu.pay.controller;


import cn.hutool.http.server.HttpServerRequest;
import com.woniu.pay.service.TuitionFeeService;
import com.woniuxy.common.entity.ResponseResult;
import com.woniuxy.common.entity.Tuition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("tuitionFee")
public class TuitionFeeController {
    @Autowired
    TuitionFeeService tuitionFeeService;



//    @RequestMapping("/get/{studentId}")
//    public ResponseResult getStudyMoney(@PathVariable Integer studentId){
//        List<Tuition> getMoney=tuitionFeeService.getStudyMoney(studentId);
//        return new ResponseResult<>(200,"ok",getMoney);
//    }

    @RequestMapping("/getAll")
    public ResponseResult getAllStudyMoney(HttpServerRequest request){
//        String token = request.getHeader("token");
//        Integer eid = Integer.valueOf(JwtUtil.getEid(token));
        Integer eid=1001;
        Integer studentId=eid;
        List<Tuition> getMoney=tuitionFeeService.getAllStudyMoney(studentId);
        return new ResponseResult<>(200,"ok",getMoney);
    }

    @RequestMapping("/getStudyYear/{studentId}")
    public ResponseResult getStudyYear(@PathVariable Integer studentId){
        List<Tuition> getYear=tuitionFeeService.getStudyYear(studentId);
        return new ResponseResult<>(200,"ok",getYear);
    }

    @RequestMapping("/pushStudyMoney")
    public ResponseResult pushStudyMoney(@RequestBody Tuition tuition){
        tuitionFeeService.pushStudyMoney(tuition);
        return new ResponseResult<>(200,"ok","通知已发送");
    }
}
