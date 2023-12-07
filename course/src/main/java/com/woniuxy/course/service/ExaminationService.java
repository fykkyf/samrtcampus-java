package com.woniuxy.course.service;

import com.woniuxy.common.entity.ExamApplication;
import com.woniuxy.common.entity.ExamSchedule;

import java.util.List;

/**
 * @author Nostalgia丶小冯
 * @date 2023/11/27 14:54
 */
public interface ExaminationService {
    List<ExamSchedule> getAllExamination();

    void addExamApplication(Integer sid,long id, ExamApplication examApplication);
}
