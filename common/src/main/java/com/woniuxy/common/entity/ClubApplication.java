package com.woniuxy.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubApplication {
	private Integer id;
	private Integer studentId;
	private String reason;
	private Date applicationTime;
	private Integer clubId;
	private Date approvalTime;
	private Integer status;
}
