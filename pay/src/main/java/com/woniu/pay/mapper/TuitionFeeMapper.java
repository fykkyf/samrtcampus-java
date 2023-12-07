package com.woniu.pay.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniuxy.common.entity.Tuition;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TuitionFeeMapper extends BaseMapper<Tuition> {


    @Update("update tuition set status = '已缴费',alipay_no=#{alipayNo},pay_time=#{payTime} where id=#{id}")
    void payStudyMoney(Tuition tuition);

    @Select("select * from tuition where id=#{id}")
    Tuition get(Integer id);

    @Select("select * from tuition where student_id=#{studentId}")
    List<Tuition> getAllStudyMoney(Integer studentId);

    @Select("select study_year from tuition where student_id=#{studentId}")
    List<Tuition> getStudyYear(Integer studentId);

    @Insert("insert into tuition values (null,#{studentId},#{tuitionFee},#{textbookFee},#{accommodationFee},#{amount},#{studyYear},2)")
    void pushStudyMoney(Tuition tuition);
}
