package com.woniu.pay.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//由前端传递 用户所在位置经纬度
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlatPunch {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer student_id;
    /*经度*/
    private String longitudeS;
    /*纬度*/
    private String latitudeS;
    private Date createTime;
}
