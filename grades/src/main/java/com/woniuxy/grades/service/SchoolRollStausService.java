package com.woniuxy.grades.service;

import com.woniuxy.grades.pojo.dto.SchoolRollDTO;
import com.woniuxy.grades.pojo.dto.StudentDTO;
import com.woniuxy.grades.pojo.vo.SchoolRollVO;
import com.woniuxy.grades.pojo.vo.StudentVO;
import com.woniuxy.grades.pojo.vo.ThesisManagementVO;
import com.woniuxy.common.entity.SchoolRollStaus;

import java.util.List;

public interface SchoolRollStausService {
    void addSchoolRollStaus(SchoolRollStaus schoolRollStaus);
    List<SchoolRollVO> selectSchoolRollBy(SchoolRollDTO schoolRollDTO);
    StudentVO selectStudentAll(StudentDTO studentDTO);

    void updateConfirmationInfor(StudentDTO studentDTO);
    ThesisManagementVO selectByStudentId(StudentDTO studentDTO);
}
