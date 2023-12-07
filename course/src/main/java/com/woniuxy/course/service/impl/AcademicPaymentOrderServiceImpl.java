package com.woniuxy.course.service.impl;

import com.woniuxy.course.entity.AcademicPaymentOrder;
import com.woniuxy.course.service.AcademicPaymentOrderService;
import com.woniuxy.course.service.SelectElectiveCourseService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Nostalgia丶小冯
 * @date 2023/11/25 10:16
 */
@Service
public class AcademicPaymentOrderServiceImpl implements AcademicPaymentOrderService {

    @Autowired
    SelectElectiveCourseService selectElectiveCourseService;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void init(Integer ecid) {
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        Integer quantity = selectElectiveCourseService.selectQuantityByEcid(ecid);
        opsForValue.set(ecid.toString(),quantity,1, TimeUnit.DAYS);
    }

    @Override
    public Integer send(Integer ecid,Integer sid) {
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        Integer list = (Integer) opsForValue.get(ecid.toString());
        System.out.println(list);
        if (list > 0){
            System.out.println("成功抢课ecid:"+ecid);
            Integer amount = selectElectiveCourseService.selectAmount(ecid);
            System.out.println("产生订单的时间：" + new Date());
            AcademicPaymentOrder academicPaymentOrder = new AcademicPaymentOrder(sid,ecid,amount);
            rabbitTemplate.convertSendAndReceive("exchangeorder","dead.order",academicPaymentOrder);
            return 200;
        } else {
            System.out.println("抢课结束，感谢参与！");
            return 400;
        }
    }
}
