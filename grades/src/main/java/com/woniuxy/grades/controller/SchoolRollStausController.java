package com.woniuxy.grades.controller;

import com.woniuxy.common.util.JwtUtil;
import com.woniuxy.grades.pojo.dto.SchoolRollDTO;
import com.woniuxy.grades.pojo.dto.StudentDTO;
import com.woniuxy.grades.pojo.vo.StudentVO;
import com.woniuxy.common.entity.ResponseResult;
import com.woniuxy.common.entity.SchoolRollStaus;
import com.woniuxy.grades.service.SchoolRollStausService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.ZoneId;
import java.util.Date;

//@CrossOrigin
@RestController
@RequestMapping("/schoolroll")
public class SchoolRollStausController {
    @Autowired
    SchoolRollStausService schoolRollStausService;
    @PostMapping("/addSchoolRollStaus")
    public Object addSchoolRollStaus(@RequestBody SchoolRollStaus schoolRollStaus,HttpServletRequest request){
        schoolRollStaus.setStudentId(Integer.valueOf(JwtUtil.getEname(request.getHeader("token"))));
        System.out.println(schoolRollStaus.getStudentId());
        if (schoolRollStaus.getType()==null){
            return new ResponseResult<Object>(401,"","请选择申请类型");
        }
        schoolRollStausService.addSchoolRollStaus(schoolRollStaus);
        return new ResponseResult<Object>(200,"",null);
    }

    @PostMapping("/selectSchoolRollBy")
    public Object selectSchoolRollBy(@RequestBody SchoolRollDTO schoolRollDTO,HttpServletRequest request){
        schoolRollDTO.setStudentId(Integer.valueOf(JwtUtil.getEname(request.getHeader("token"))));
        System.out.println(schoolRollDTO);
        return new ResponseResult<Object>(200,"", schoolRollStausService.selectSchoolRollBy(schoolRollDTO));
    }

    @GetMapping("/selectStudentAll")
    public Object selectStudentAll(HttpServletRequest request){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(Integer.valueOf(JwtUtil.getEname(request.getHeader("token"))));
        StudentVO studentVO = schoolRollStausService.selectStudentAll(studentDTO);
        int year1 = studentVO.getGraduationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear();
        int year2 = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear();
        if (year1==year2){
            return new ResponseResult<Object>(200,"", studentVO);
        }else{
            return new ResponseResult<Object>(202,"", studentVO);
        }
    }

    @GetMapping("/updateConfirmationInfor")
    public Object updateConfirmationInfor( HttpServletRequest request){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(Integer.valueOf(JwtUtil.getEname(request.getHeader("token"))));
        schoolRollStausService.updateConfirmationInfor(studentDTO);
        return new ResponseResult<Object>(200,"", null);
    }

    //查看论文进度
    @GetMapping("/selectThesisByStudentId")
    public Object selectByStudentId(HttpServletRequest request){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(Integer.valueOf(JwtUtil.getEname(request.getHeader("token"))));
        if (schoolRollStausService.selectByStudentId(studentDTO)!=null) {
            return new ResponseResult<Object>(200,"", schoolRollStausService.selectByStudentId(studentDTO));
        }else{
            return new ResponseResult<Object>(401,"", "暂无数据！");
        }

    }


}
