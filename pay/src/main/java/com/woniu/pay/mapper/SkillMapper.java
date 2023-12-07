package com.woniu.pay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniuxy.common.entity.Skill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface SkillMapper extends BaseMapper<Skill> {

    @Update("update skill set quantity=quantity+1 where name=#{name}")
    void update(String name);
}
