package com.woniuxy.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@TableName("express_delivery")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Express {
	private String studentName;
	private Integer phoneNumber;
	private String receiver;
	private Integer receiverPhone;
	private Integer items;
	private Double amount;
	private String location;
	private Date time;
	private Integer status;

}
