package com.woniuxy.common.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SkillExamPayment {
    private Integer id;
    private String alipayNo;
    private Integer studentId;
    private String type;
    private Double amount;
    @JsonFormat(pattern = "yyyy年MM月dd日 HH时mm分ss秒", timezone = "GMT+8")
    private Date createTime;
//    @JsonFormat(pattern = "yyyy年MM月dd日 HH时mm分ss秒", timezone = "GMT+8")
//    private Date payTime;
    private String payTime;
    private String status;
}
