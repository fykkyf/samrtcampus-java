package com.woniuxy.course.config;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfiguration {
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue queueorder(){
        return new Queue("queueorder");
    }
    @Bean
    public Queue queueorderdead(){
        Map<String,Object> arguments = new HashMap<>();
        //设置消息的等待时间
        arguments.put("x-message-ttl",3000000);
        //变成死信以后，进入那个交换机
        arguments.put("x-dead-letter-exchange","exchangeorderdlx");
        //进入死信交换机以后，通过指定的路由，进入死信队列
        arguments.put("x-dead-letter-routing-key","orderdlx");
        return new Queue("queueorderdead",true,false,false,arguments);
    }
    @Bean
    public TopicExchange exchangeorder(){
        return new TopicExchange("exchangeorder");
    }

    @Bean
    public Binding bindingqueueordertoexchangeorder(Queue queueorder,TopicExchange exchangeorder){
        return BindingBuilder.bind(queueorder).to(exchangeorder).with("#.order.#");
    }
    @Bean
    public Binding bindingqueueorderdeadtoexchangeorder(Queue queueorderdead,TopicExchange exchangeorder){
        return BindingBuilder.bind(queueorderdead).to(exchangeorder).with("#.dead.#");
    }

    @Bean
    public Queue queueorderdlx(){
        return new Queue("queueorderdlx");
    }
    @Bean
    public TopicExchange exchangeorderdlx(){
        return new TopicExchange("exchangeorderdlx");
    }
    @Bean
    public Binding bindingqueueorderdlxtoexchangeorderdlx(Queue queueorderdlx,TopicExchange exchangeorderdlx) {
        return BindingBuilder.bind(queueorderdlx).to(exchangeorderdlx).with("#.orderdlx.#");
    }
}
