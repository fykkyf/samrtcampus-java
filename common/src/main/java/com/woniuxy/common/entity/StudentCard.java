package com.woniuxy.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentCard {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer studentId;
    private Integer cardId;
    private Double cardBalance;
    private String status;
}
