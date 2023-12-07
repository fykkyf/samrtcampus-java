package com.woniuxy.grades.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ThesisManagementVO {
    private Integer thesisId;
    private String tname;
    private String thesisTitle;
    private Integer status;
}
