package com.woniuxy.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbsenceApplication {
	private Integer id;
	private Integer studentId;
	private String reason;
	private Date time;
	private Date startTime;
	private Date endTime;
	private Integer days;
	private Date approvalTime;
	private Integer status;
}
