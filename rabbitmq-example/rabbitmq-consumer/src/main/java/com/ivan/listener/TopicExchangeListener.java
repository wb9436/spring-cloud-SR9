package com.ivan.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Topic Exchange（主题(通配符) -交换机）
 * -- 把消息转发给所有绑定到交换机的队列，且符合 routing pattern 的队列
 * -- #：匹配一个或多个词
 * -- *：匹配不多不少恰好1个词
 *
 * @author: WB
 * @version: v1.0
 */
@Slf4j
@Component
public class TopicExchangeListener {

    /**
     * #：匹配一个或多个词
     * *：匹配不多不少恰好1个词
     */
    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue(name = "topic.china.queue"),
            exchange = @Exchange(name = "topic.exchange", type = ExchangeTypes.TOPIC),
            key = "china.#"
    )})
    public void listenerAllChinaMsg(String msg) {
        log.info("listener1 接收到 Topic Exchange【{}】 RoutingKey 【{}】 消息内容为：{}",
                "topic.china.queue", "china.#", msg);
    }

    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue(name = "topic.news.queue"),
            exchange = @Exchange(name = "topic.exchange", type = ExchangeTypes.TOPIC),
            key = "#.news"
    )})
    public void listenerAllNewsMsg(String msg) {
        log.info("listener2 接收到 Topic Exchange【{}】 RoutingKey 【{}】 消息内容为：{}",
                "topic.news.queue", "#.news", msg);
    }
}
