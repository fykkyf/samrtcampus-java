package com.woniuxy.grades.service;

import com.woniuxy.grades.pojo.dto.StudentDTO;
import com.woniuxy.grades.pojo.vo.GradeVO;

import java.util.List;

public interface GradeService {
    List<GradeVO> selectGradeByStuId(StudentDTO studentDTO);
}
