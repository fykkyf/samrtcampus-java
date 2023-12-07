package com.woniuxy.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ThesisManagement {
    private Integer thesisId;
    private Integer studentId;
    private Integer teacherId;
    private String thesisTitle;
    private Integer status;
}
