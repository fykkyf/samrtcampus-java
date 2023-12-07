package com.woniuxy.course.service.impl;

import com.woniuxy.common.entity.ExamApplication;
import com.woniuxy.common.entity.ExamSchedule;
import com.woniuxy.course.mapper.ExaminationMapper;
import com.woniuxy.course.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Nostalgia丶小冯
 * @date 2023/11/27 19:09
 */
@Service
public class ExaminationServiceImpl implements ExaminationService {
    @Autowired
    ExaminationMapper examinationMapper;

    @Override
    public List<ExamSchedule> getAllExamination() {
        return examinationMapper.selectAllExamination();
    }

    @Override
    public void addExamApplication(Integer sid,long id,ExamApplication examApplication) {
        examinationMapper.addExamApplication(sid, id,examApplication);
    }
}
