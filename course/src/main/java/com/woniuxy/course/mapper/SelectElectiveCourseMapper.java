package com.woniuxy.course.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniuxy.common.entity.AcademicPayment;
import com.woniuxy.common.entity.ElectiveCourse;
import com.woniuxy.course.entity.ElectiveCourseInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Nostalgia丶小冯
 * @date 2023/11/23 19:27
**/
@Mapper
public interface SelectElectiveCourseMapper extends BaseMapper<ElectiveCourse> {
    List<ElectiveCourse> selectAllElectiveCourse();

    List<ElectiveCourse> selectElectiveCourse(ElectiveCourseInfo electiveCourseInfo);

    void choiceElectiveCourse(@Param("sid") Integer sid,@Param("ecid")Integer ecid,@Param("amount") Integer amount);

    @Select("select amount from elective_course where ecid = #{ecid}")
    Integer selectAmountByEcid(Integer ecid);

    @Update("update  elective_course set quantity = quantity - 1 where ecid = #{ecid}")
    void updateElectiveCourseQuantity(Integer ecid);

    @Select("select quantity from elective_course where ecid = #{ecid};")
    Integer selectQuantityByEcid(Integer ecid);

    @Select("select ecid from elective_course;")
    List<Integer> selectAllElectiveCourseEcid();

    @Select("select status from academic_payment where student_id = #{sid} and elective_course_id = #{ecid};")
    String getAcademicPayment(@Param("sid") Integer sid,@Param("ecid") Integer ecid);

    @Update("update  elective_course set quantity = quantity + 1 where ecid = #{ecid}")
    void changeElectiveCourseQuantity(Integer ecid);

    @Delete("delete from academic_payment where student_id = #{sid} and elective_course_id = #{ecid}")
    void delectAcademicPayment(@Param("sid") Integer sid,@Param("ecid") Integer ecid);

    @Select("select count(*) from academic_payment where student_id = #{sid} and elective_course_id = #{ecid}")
    int findAcademicPaymentOrder(@Param("sid") Integer sid,@Param("ecid") Integer ecid);

    @Select("select elective_course_id from academic_payment where student_id = #{sid}")
    Integer[] selectAllElectiveCoursePay(Integer sid);

    List<AcademicPayment> selectAcademicPayment(@Param("sid") Integer sid);
}
