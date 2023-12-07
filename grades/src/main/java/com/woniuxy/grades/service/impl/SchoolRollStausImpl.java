package com.woniuxy.grades.service.impl;

import com.woniuxy.common.entity.SchoolRollStaus;
import com.woniuxy.grades.mapper.SchoolRollStausMapper;
import com.woniuxy.grades.pojo.dto.SchoolRollDTO;
import com.woniuxy.grades.pojo.dto.StudentDTO;
import com.woniuxy.grades.pojo.vo.SchoolRollVO;
import com.woniuxy.grades.pojo.vo.StudentVO;
import com.woniuxy.grades.pojo.vo.ThesisManagementVO;
import com.woniuxy.grades.service.SchoolRollStausService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolRollStausImpl implements SchoolRollStausService {
    @Autowired
    SchoolRollStausMapper schoolRollStausMapper;

    @Override
    public void addSchoolRollStaus(SchoolRollStaus schoolRollStaus) {
        schoolRollStausMapper.addSchoolRollStaus(schoolRollStaus);
    }

    @Override
    public List<SchoolRollVO> selectSchoolRollBy(SchoolRollDTO schoolRollDTO) {
        return schoolRollStausMapper.selectSchoolRollBy(schoolRollDTO);
    }

    @Override
    public StudentVO selectStudentAll(StudentDTO studentDTO) {
        return schoolRollStausMapper.selectStudentAll(studentDTO);
    }

    @Override
    public void updateConfirmationInfor(StudentDTO studentDTO) {
        schoolRollStausMapper.updateConfirmationInfor(studentDTO);
    }

    @Override
    public ThesisManagementVO selectByStudentId(StudentDTO studentDTO) {
        return schoolRollStausMapper.selectByStudentId(studentDTO);
    }
}
