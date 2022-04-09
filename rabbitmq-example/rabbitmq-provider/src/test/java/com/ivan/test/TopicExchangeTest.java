package com.ivan.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Topic Exchange 测试
 *
 * @author: WB
 * @version: v1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicExchangeTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testTopicExchange1() {
        // 交换机名称
        String exchangeName = "topic.exchange";
        // routingKey
        String routingKey = "china.shanghai";
        // 消息
        String message = "中国-上海又有新冠疫情了！！！";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, routingKey, routingKey + " : " + message);
    }

    @Test
    public void testTopicExchange2() {
        // 交换机名称
        String exchangeName = "topic.exchange";
        // routingKey
        String routingKey = "china.news";
        // 消息
        String message = "中国-上海又有新冠疫情了！！！";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, routingKey, routingKey + " : " + message);
    }

    @Test
    public void testTopicExchange3() {
        // 交换机名称
        String exchangeName = "topic.exchange";
        // routingKey
        String routingKey = "shanghai.news";
        // 消息
        String message = "中国-上海又有新冠疫情了！！！";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, routingKey, routingKey + " : " + message);
    }


}
