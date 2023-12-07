package com.woniuxy.grades.service.impl;

import com.woniuxy.grades.mapper.GradeMapper;
import com.woniuxy.grades.pojo.dto.StudentDTO;
import com.woniuxy.grades.pojo.vo.GradeVO;
import com.woniuxy.grades.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeImpl implements GradeService {
    @Autowired
    GradeMapper gradeMapper;
    @Override
    public List<GradeVO> selectGradeByStuId(StudentDTO studentDTO) {
        return gradeMapper.selectGradeByStuId(studentDTO);
    }
}
