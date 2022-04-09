package com.ivan.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Direct Exchange (定向交换机)
 * -- 把消息转发给所有绑定到交换机，且符合 RoutingKey 的队列
 *
 * @author: WB
 * @version: v1.0
 */
@Slf4j
@Component
public class DirectExchangeListener {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue1"),
            exchange = @Exchange(name = "direct.exchange", type = ExchangeTypes.DIRECT),
            key = {"red", "blue"} // 可接收 red、blue 的路由消息
    ))
    public void listenerDirectQueue1(String msg) {
        log.info("listenerDirectQueue1 接收到 Fanout Exchange【{}】消息内容为：{}", "direct.queue1", msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue2"),
            exchange = @Exchange(name = "direct.exchange", type = ExchangeTypes.DIRECT),
            key = {"red", "yellow"} // 可接收 red、yellow 的路由消息
    ))
    public void listenerDirectQueue2(String msg) {
        log.info("listenerDirectQueue2 接收到 Fanout Exchange【{}】消息内容为：{}", "direct.queue2", msg);
    }

}
