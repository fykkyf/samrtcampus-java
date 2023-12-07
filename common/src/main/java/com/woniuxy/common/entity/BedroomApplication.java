package com.woniuxy.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BedroomApplication {
	private Integer id;
	private String studentName;
	private Integer phone;
	private Date time;
	private String location;
	private Integer type;
	private Integer status;
}
