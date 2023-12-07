package com.woniuxy.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentClubAssociation {
    private Integer id;
    private Integer studentId;
    private Integer clubId;
}
