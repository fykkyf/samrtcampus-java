package com.woniuxy.grades.mapper;

import com.woniuxy.common.entity.SchoolRollStaus;
import com.woniuxy.grades.pojo.dto.SchoolRollDTO;
import com.woniuxy.grades.pojo.dto.StudentDTO;
import com.woniuxy.grades.pojo.vo.SchoolRollVO;
import com.woniuxy.grades.pojo.vo.StudentVO;
import com.woniuxy.grades.pojo.vo.ThesisManagementVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface SchoolRollStausMapper {
    @Insert("insert into school_roll_status values(null,#{studentId},#{type},1,now(),null)")
    void addSchoolRollStaus(SchoolRollStaus schoolRollStaus);

    //查看学籍申请
    List<SchoolRollVO> selectSchoolRollBy(SchoolRollDTO schoolRollDTO);

    //查出所有  出毕业日期，业务层判断是否今年毕业，把判断结果传给前端
    @Select("select s.*,fm.name from student s,faculty_major fm where student_id=#{studentId} and s.department_id=fm.id")
    StudentVO selectStudentAll(StudentDTO studentDTO);

    //确认毕业生信息
    @Update("update student set confirmation_infor=2 where student_id = #{studentId}")
    void updateConfirmationInfor(StudentDTO studentDTO);

    //查看毕业论文进度
    @Select("select tm.thesis_id,tc.tname,tm.thesis_title,tm.`status`  from thesis_management tm,teacher tc " +
            "where tm.student_id =#{studentId}  and tm.teacher_id=tc.teacher_id ")
    ThesisManagementVO selectByStudentId(StudentDTO studentDTO);
}
