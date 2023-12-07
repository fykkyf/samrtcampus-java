package com.woniuxy.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SchoolRollStaus {
    private Integer id;
    private Integer studentId;
    private Integer type;
    private Integer status;
    private Date submissionTime;
    private Date approvalTime;
}
