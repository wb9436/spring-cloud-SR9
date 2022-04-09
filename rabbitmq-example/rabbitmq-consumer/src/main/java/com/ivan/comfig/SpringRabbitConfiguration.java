package com.ivan.comfig;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: WB
 * @version: v1.0
 */
@Configuration
public class SpringRabbitConfiguration {

    /**
     * 声明一个队列
     * 可在@RabbitListener注解中声明
     */
    @Bean
    public Queue simpleQueue() {
        return new Queue("simple.queue");
    }

    /**
     * 声明一个队列
     * 可在@RabbitListener注解中声明
     */
    @Bean
    public Queue fanoutQueue1() {
        return new Queue("fanout.queue1");
    }

    /**
     * 声明一个交换机（指明交换机类型）
     * 可在@RabbitListener注解中声明
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout.exchange");
    }

    /**
     * 声明一个binding将队列绑定到交换机上
     * 可在@RabbitListener注解中声明
     */
    @Bean
    public Binding fanoutBinding1(Queue fanoutQueue1, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
    }

    /**
     * 声明一个队列
     * 可在@RabbitListener注解中声明
     */
    @Bean
    public Queue fanoutQueue2() {
        return new Queue("fanout.queue2");
    }


    /**
     * 声明一个binding将队列绑定到交换机上
     * 可在@RabbitListener注解中声明
     */
    @Bean
    public Binding fanoutBinding2(Queue fanoutQueue2, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
    }

}
