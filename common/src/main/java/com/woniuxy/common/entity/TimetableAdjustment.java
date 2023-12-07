package com.woniuxy.common.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimetableAdjustment {
    private Integer id;
    private Integer scheduleId;
    private Integer classroomId;
}
