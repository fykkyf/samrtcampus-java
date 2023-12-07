package com.woniuxy.login.conf;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
    //声明队列
    @Bean
    public Queue queuehellworld(){
        return new Queue("queuehelloworld");
    }
    @Bean
    public Queue queuephone(){
        return new Queue("queuephone");
    }
    @Bean
    public Queue queuesms(){
        return new Queue("queuesms");
    }
    //定义交换机,发布订阅模式使用fanout类型交换机
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }

    //将队列绑定到交换机
    @Bean
    public Binding bindingqueuephonetofanoutExchange(Queue queuephone,FanoutExchange fanoutExchange){
        //return BindingBuilder.bind(queuephone()).to(fanoutExchange());
        return BindingBuilder.bind(queuephone).to(fanoutExchange);
    }
    @Bean
    public Binding bindingqueuesmstofanoutExchange(Queue queuesms,FanoutExchange fanoutExchange){
        //return BindingBuilder.bind(queuephone()).to(fanoutExchange());
        return BindingBuilder.bind(queuesms).to(fanoutExchange);
    }

    //路由模式，使用direct类型交换机
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("directExchange");
    }
    @Bean
    public Binding bindingqueuephonetodirectExchange(Queue queuephone,DirectExchange directExchange){
        //return BindingBuilder.bind(queuephone()).to(fanoutExchange());
        return BindingBuilder.bind(queuephone).to(directExchange).with("phone");
    }
    @Bean
    public Binding bindingqueuesmstodirectExchange(Queue queuesms,DirectExchange directExchange){
        //return BindingBuilder.bind(queuephone()).to(fanoutExchange());
        return BindingBuilder.bind(queuesms).to(directExchange).with("sms");
    }

    //topisc模式使用topic类型的交换机
    @Bean
    public TopicExchange exchangetopic(){
        return new TopicExchange("exchangetopic");
    }

    @Bean
    public Binding bindingqueuephonetoexchangetopic(Queue queuephone, TopicExchange exchangetopic){
        return BindingBuilder.bind(queuephone).to(exchangetopic).with("#.phone.#");
    }

    @Bean
    public Binding bindingqueuesmstoexchangetopic(Queue queuesms, TopicExchange exchangetopic){
        return BindingBuilder.bind(queuesms).to(exchangetopic).with("#.sms.#");
    }
}
