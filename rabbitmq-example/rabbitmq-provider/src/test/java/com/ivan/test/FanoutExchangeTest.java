package com.ivan.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Fanout Exchange 测试
 *
 * @author: WB
 * @version: v1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FanoutExchangeTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testFanoutExchange() {
        // 声明交换机名称
        String fanoutExchange = "fanout.exchange";
        String msg = "测试发送 Fanout Exchange 消息";
        for (int i = 0; i < 3; i++) {
            // 使用 Fanout Exchange，可不需要指定routingKey
            rabbitTemplate.convertAndSend(fanoutExchange, null, msg + i);
        }
    }

    @Test
    public void testFanoutExchange2() {
        // 声明交换机名称
        String fanoutExchange = "fanout.exchange";
        String msg = "测试发送 Fanout Exchange 消息";
        for (int i = 0; i < 2; i++) {
            // 使用 Fanout Exchange
            // -- 可不需要指定routingKey
            // -- 指定也无效（依然会对绑定当前交换机的所有队列进行消息广播）
            rabbitTemplate.convertAndSend(fanoutExchange, "fanout.queue2", msg + i);
        }
    }

}
