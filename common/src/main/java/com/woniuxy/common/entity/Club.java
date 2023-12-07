package com.woniuxy.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Club {
	private Integer cid;
	private String clubName;
	private Integer members;
	private Integer level;
	private Integer leaderId;

}
