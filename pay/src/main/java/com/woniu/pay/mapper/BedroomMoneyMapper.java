package com.woniu.pay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.woniuxy.common.entity.BedroomMoney;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface BedroomMoneyMapper extends BaseMapper<BedroomMoney> {

    @Update("update bedroom_money set water_balance = water_balance + #{waterBalance} where bedroom_id=#{bedroomId}")
    void updateWaterBalance(@Param("waterBalance") Double waterBalance,@Param("bedroomId") Integer bedroomId);

    @Update("update bedroom_money set electricity_balance = electricity_balance + #{electricityBalance} where bedroom_id=#{bedroomId}")
    void updateElectricityBalance(@Param("electricityBalance") Double electricityBalance,@Param("bedroomId") Integer bedroomId);
}
