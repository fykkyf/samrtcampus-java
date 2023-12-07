package com.woniu.pay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniuxy.common.entity.LivingExpense;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface LivingExpenseMapper extends BaseMapper<LivingExpense> {

    @Select("select * from living_expense where student_id=#{studentId}")
    List<LivingExpense> selectByStudentId(Integer studentId);

    @Update("update living_expense set alipay_no=#{alipayNo},pay_time= #{payTime} where id = #{id}")
    void update(LivingExpense livingExpense);

    @Select("select * from living_expense where id=#{id}")
    LivingExpense get(Integer id);

    @Select("select * from living_expense where student_id=#{studentId} and type='饭卡'")
    List<LivingExpense> selectByStudentIdAndStatus(Integer studentId);
}
