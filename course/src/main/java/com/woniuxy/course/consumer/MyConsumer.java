package com.woniuxy.course.consumer;

import com.rabbitmq.client.Channel;
import com.woniuxy.course.entity.AcademicPaymentOrder;
import com.woniuxy.course.mapper.SelectElectiveCourseMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class MyConsumer {
    @Autowired
    SelectElectiveCourseMapper selectElectiveCourseMapper;

    //这个注解就是让这个方法监听指定的队列，接收消息，处理消息
    //String str 消息体，可以写object
    //Message message 完整的消息对象，保护其他信息
    //Channel channel 信道对象
    @RabbitListener(queues = "queueorder")
    public void receive(AcademicPaymentOrder academicPaymentOrder, Message message, Channel channel) throws Exception {
        System.out.println("消费者1收到消息：" + academicPaymentOrder);
        selectElectiveCourseMapper.choiceElectiveCourse(academicPaymentOrder.getSid(), academicPaymentOrder.getEcid(),academicPaymentOrder.getAmount());
        selectElectiveCourseMapper.updateElectiveCourseQuantity(academicPaymentOrder.getEcid());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

    @RabbitListener(queues = "queueorderdlx")
    public void receiveQueueorderdlx(AcademicPaymentOrder academicPaymentOrder, Message message, Channel channel) throws Exception {
        System.out.println("10s后查看订单状态，没有支付，修改成不能支付的状态" + new Date());
        String status = selectElectiveCourseMapper.getAcademicPayment(academicPaymentOrder.getSid(),academicPaymentOrder.getEcid());
        if (status.equals("未缴费")){
            //未支付将数量修改回去
            selectElectiveCourseMapper.changeElectiveCourseQuantity(academicPaymentOrder.getEcid());
            //支付超时删除订单
            selectElectiveCourseMapper.delectAcademicPayment(academicPaymentOrder.getSid(),academicPaymentOrder.getEcid());
        }
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}
