package com.woniu.pay.controller;

import cn.hutool.http.server.HttpServerRequest;
import com.woniu.pay.common.PlatPunch;
import com.woniu.pay.mapper.PlatPunchMapper;
import com.woniuxy.common.entity.ResponseResult;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static com.woniu.pay.common.GetDistanceMeter.getDistanceMeter;

@RestController
@RequestMapping("sign")
public class SignController {
    @Autowired
    PlatPunchMapper platPunchMapper;

    /*
    活动定位打卡功能 */
    @RequestMapping("/sign")
    public ResponseResult getPunch(@RequestBody PlatPunch platPunch, HttpServerRequest request){
        //1、设置目的地经度
        String longitudeS = "104.0700878";
        //设置目的纬度
        String latitudeS = "30.5817478";
        // 2、由前端传过来的用户所在位置 经纬度
        System.out.println("传过来的精度："+platPunch.getLongitudeS());
        System.out.println("传过来的纬度："+platPunch.getLatitudeS());
        String longitudeT = platPunch.getLongitudeS();
        String latitudeT = platPunch.getLatitudeS();
//        String longitudeT = "104.0610723740061";
//        String latitudeT = "30.579284915576896 ";
        // 3、对比
        GlobalCoordinates source = new GlobalCoordinates(Double.parseDouble(latitudeS),Double.parseDouble(longitudeS));
        GlobalCoordinates target = new GlobalCoordinates(Double.parseDouble(latitudeT),Double.parseDouble(longitudeT));
        //这是两种坐标系计算方法，这是第一种坐标系计算方法（我们用的这种）
        double geoCurve = getDistanceMeter(source, target, Ellipsoid.Sphere);
        //这是两种坐标系计算方法，这是第二种坐标系计算方法
        double geoCurve2 = getDistanceMeter(source, target, Ellipsoid.WGS84);
        System.out.println(geoCurve+"----------"+geoCurve2);
        //如果用户和目的地位置想吃2千米，在不能打卡；
        if(geoCurve > 1500){
            System.out.println("不能打卡！！！");
            //反之，可以打卡
            return new ResponseResult<>(401,"error","不在范围内，打卡失败");
        }else {
//            String token = request.getHeader("token");
//            Integer eid = Integer.valueOf(JwtUtil.getEid(token));
            Integer eid=1001;
            Integer studentId=eid;
            platPunch.setStudent_id(studentId);
            platPunch.setCreateTime(new Date());
            platPunchMapper.insert(platPunch);
            System.out.println("能打卡");
            return new ResponseResult<>(200,"ok","打卡成功");
        }
    }
}
