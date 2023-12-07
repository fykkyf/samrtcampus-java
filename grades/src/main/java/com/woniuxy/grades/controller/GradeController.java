package com.woniuxy.grades.controller;

import com.woniuxy.grades.pojo.dto.StudentDTO;
import com.woniuxy.grades.service.GradeService;
import com.woniuxy.common.entity.ResponseResult;
import com.woniuxy.common.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.ZoneId;

//@CrossOrigin
@RestController
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    GradeService gradeService;
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    @PostMapping("/selectGradeByStuId")
    public Object selectGradeByStuId(@RequestBody StudentDTO studentDTO, HttpServletRequest request){
        if (studentDTO.getMinAcademicYear()==null || studentDTO.getMaxAcademicYear()==null || studentDTO.getSemester()==null){
            return new ResponseResult<Object>(401,"","请选择学年和学期");
        }else {
            String year1 = String.valueOf(studentDTO.getMinAcademicYear().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear());
            String year2 = String.valueOf(studentDTO.getMaxAcademicYear().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear());
            studentDTO.setAcademicYear(year1+"-"+year2);

            studentDTO.setStudentId(Integer.valueOf(JwtUtil.getEname(request.getHeader("token"))));
            System.out.println(studentDTO);
            return new ResponseResult<Object>(200,"",gradeService.selectGradeByStuId(studentDTO));
        }
    }
}
