package com.woniu.pay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniuxy.common.entity.Skill;
import com.woniuxy.common.entity.SkillExamPayment;
import com.woniuxy.common.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface SkillPaymentMapper extends BaseMapper<SkillExamPayment> {
    @Select("select * from skill")
    List<Skill> getAll();

    @Select("select * from skill_exam_payment where id=#{id}")
    SkillExamPayment get(Integer id);

    @Select("select count(*) from passed_cet4 where student_id=#{studentId}")
    Integer getMessage(Integer studentId);

    @Insert("insert into skill_exam_payment values (null,null,#{studentId},#{type},#{amount},#{createTime},null,#{status})")
    void add(SkillExamPayment skillExamPayment);

    @Update("update skill set quantity=quantity-1 where id=#{id}")
    void reduce(Integer id);

    @Select("select * from skill_exam_payment where student_id=#{studentId}")
    List<SkillExamPayment> getMoney(Integer studentId);

    @Update("update skill_exam_payment set alipay_no=#{alipayNo},pay_time= #{payTime},status='已缴费' where id = #{id}")
    void pay(SkillExamPayment skillExamPayment);

    @Delete("delete from skill_exam_payment where id=#{id}")
    void delete(Integer id);


    //根据学生的id去查学生表中的信息
    @Select("select * from student where student_id=#{studentId}")
    Student findBySid(Integer studentId);

    @Select("select count(*) from skill_exam_payment where student_id=#{studentId} and type=#{type}")
    Integer getCount(@Param("studentId") Integer studentId,@Param("type") String type);
}
