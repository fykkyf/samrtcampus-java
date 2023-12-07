package com.woniuxy.login.consumer;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.mail.MailUtil;
import com.rabbitmq.client.Channel;
import com.woniuxy.login.util.SmsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class Myconsumer {
    //这个注解，就是让这个方法监听指定的队列，接收消息，处理消息

    //String str,   消息体，可以写Object
    // Message message, 完整的消息对象,保护其他信息
    // Channel channel,信道对象
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    @RabbitListener(queues="queuephone")
    public void receiveQueueemail(String phone, Message message, Channel channel) throws Exception {
        //生成随机的验证码
        String code = RandomUtil.randomNumbers(4);

        //存储验证码到redis中并设置过期时间
        redisTemplate.opsForValue().set(phone,code,300, TimeUnit.SECONDS);
        //写入邮件内容，发送邮件
        SmsUtil.sendCode(phone, code);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

}
