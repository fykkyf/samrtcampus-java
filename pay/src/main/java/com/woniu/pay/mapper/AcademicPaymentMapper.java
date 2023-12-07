package com.woniu.pay.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniuxy.common.entity.AcademicPayment;
import com.woniuxy.common.entity.Tuition;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AcademicPaymentMapper extends BaseMapper<AcademicPayment> {

    @Insert("insert into academic_payment values (null,#{studentId},#{type},#{amount},#{time},#{status})")
    void generated(AcademicPayment academicPayment);

    @Select("select * from academic_payment where student_id=#{studentId}")
    List<AcademicPayment> getAllMoney(Integer studentId);

//    @Update("update academic_payment set status=1 where type=1 and id=#{id}")
//    void payMoney(Integer id);

    @Select("select * from academic_payment where id =#{id}")
    AcademicPayment get(Integer id);

    @Update("update academic_payment set status = '已支付',alipay_no=#{alipayNo},pay_time=#{payTime} where id=#{id}")
    void payMoney(AcademicPayment academicPayment);
}
