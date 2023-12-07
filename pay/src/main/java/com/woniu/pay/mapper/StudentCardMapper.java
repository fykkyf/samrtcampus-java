package com.woniu.pay.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniuxy.common.entity.StudentCard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface StudentCardMapper extends BaseMapper<StudentCard> {
    @Update("update student_card set card_balance = card_balance + #{cardBalance} where card_id=#{cardId}")
    void updateBalance(@Param("cardBalance") Double cardBalance,@Param("cardId") Integer cardId);

    @Update("update student_card set status='挂失' where card_id=#{cardId}")
    void updateStatus(StudentCard studentCard);

    @Update("update student_card set status='停用',card_balance=0 where card_id=#{cardId} ")
    void updateCauseReissue(Integer cardId);
}
