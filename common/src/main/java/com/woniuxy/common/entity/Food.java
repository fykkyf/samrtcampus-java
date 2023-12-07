package com.woniuxy.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Food {
	private Integer mid;
	private String name;
	private Integer phone;
	private String address;
	private Integer parentId;
	private Double price;
	private List<Food> foods;
}
