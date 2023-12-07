package com.woniuxy.course.mapper;

import com.woniuxy.common.entity.ExamApplication;
import com.woniuxy.common.entity.ExamSchedule;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Nostalgia丶小冯
 * @date 2023/11/27 14:52
 */
@Mapper
public interface ExaminationMapper {
    List<ExamSchedule> selectAllExamination();

    @Insert("insert into exam_application values (#{id},#{sid},#{examApplication.courseId},#{examApplication.type},1,now())")
    void addExamApplication(@Param("sid") Integer sid,@Param("id") long id,@Param("examApplication") ExamApplication examApplication);
}
