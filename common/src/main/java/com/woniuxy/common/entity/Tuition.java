package com.woniuxy.common.entity;

import cn.hutool.core.date.DateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tuition {
    private Integer id;
    private String alipayNo;
    private Integer studentId;
    private Double tuitionFee;
    private Double textbookFee;
    private Double accommodationFee;
    private Double amount;
    @JsonFormat(pattern = "yyyy年MM月dd日 HH时mm分ss秒", timezone = "GMT+8")
    private Date createTime;
    private String payTime;
    private String status;
}
