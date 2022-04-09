package com.ivan.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Direct Exchange 测试
 *
 * @author: WB
 * @version: v1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DirectExchangeTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testDirectExchange1() {
        // 交换机名称
        String exchangeName = "direct.exchange";
        // 消息
        String message = "red 消息！";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, "red", message);
    }

    @Test
    public void testDirectExchange2() {
        // 交换机名称
        String exchangeName = "direct.exchange";
        // 消息
        String message = "blue 消息！";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, "blue", message);
    }

    @Test
    public void testDirectExchange3() {
        // 交换机名称
        String exchangeName = "direct.exchange";
        // 消息
        String message = "yellow消息！";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, "yellow", message);
    }
}
