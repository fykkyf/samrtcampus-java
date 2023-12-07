package com.woniuxy.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class FoodOrder {
	private Integer id;
	private Integer studentId;
	private Integer phone;
	private Integer dishId;
	private Integer number;
	private Double amount;
}
