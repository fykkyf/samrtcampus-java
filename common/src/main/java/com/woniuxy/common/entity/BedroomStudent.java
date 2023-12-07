package com.woniuxy.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class BedroomStudent {
    private Integer id;
    private Integer bedroomId;
    private Integer studentId;
}
