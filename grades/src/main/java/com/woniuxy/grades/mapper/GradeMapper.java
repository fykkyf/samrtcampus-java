package com.woniuxy.grades.mapper;

import com.woniuxy.grades.pojo.dto.StudentDTO;
import com.woniuxy.grades.pojo.vo.GradeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GradeMapper {
      //查询成绩
      List<GradeVO> selectGradeByStuId(StudentDTO studentDTO);
}
