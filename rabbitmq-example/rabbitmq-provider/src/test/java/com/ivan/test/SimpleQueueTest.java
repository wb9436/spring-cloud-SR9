package com.ivan.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * 简单队列测试
 *
 * @author: WB
 * @version: v1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleQueueTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSimpleQueue() {
        for (int i = 0; i < 4; i++) {
            String msg = "测试发送简单队列消息" + i;
            rabbitTemplate.convertAndSend("simple.queue", msg);
        }
    }

    @Test
    public void testSimpleQueue2() {
        String msg = "测试发送简单队列消息2";
        rabbitTemplate.convertAndSend("simple.queue2", msg);
    }

    @Test
    public void testSendMap() {
        // 准备消息
        Map<String, Object> msg = new HashMap<>();
        msg.put("name", "Jack");
        msg.put("age", 21);
        // 发送消息
        rabbitTemplate.convertAndSend("simple.queue.map", msg);
    }
}
