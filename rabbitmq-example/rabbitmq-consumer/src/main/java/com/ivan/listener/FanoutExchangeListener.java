package com.ivan.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Fanout Exchange（扇出 - 广播交换机）
 * -- 把消息转发给所有绑定到交换机的队列，不需要指定 RoutingKey
 *
 * @author: WB
 * @version: v1.0
 */
@Slf4j
@Component
public class FanoutExchangeListener {

    @RabbitListener(queues = "fanout.queue1")
    public void listenerFanoutQueue1(String msg) {
        log.info("listenerFanoutQueue1 接收到 Fanout Exchange【{}】消息内容为：{}", "fanout.queue1", msg);
    }

    @RabbitListener(queues = "fanout.queue1")
    public void listenerFanoutQueue2(String msg) {
        log.info("listenerFanoutQueue2 接收到 Fanout Exchange【{}】消息内容为：{}", "fanout.queue1", msg);
    }

    @RabbitListener(queues = "fanout.queue2")
    public void listenerFanoutQueue3(String msg) {
        log.info("listenerFanoutQueue3 接收到 Fanout Exchange【{}】消息内容为：{}", "fanout.queue2", msg);
    }

    /**
     * 可使用注解方式：
     * -- 声明队列
     * -- 声明交换机（指定类型）
     * -- 绑定队列到交换机
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "fanout.queue3"),
            exchange = @Exchange(name = "fanout.exchange", type = ExchangeTypes.FANOUT)
    ))
    public void listenerFanoutQueue4(String msg) {
        log.info("listenerFanoutQueue4 接收到 Fanout Exchange【{}】消息内容为：{}", "fanout.queue3", msg);
    }
}
