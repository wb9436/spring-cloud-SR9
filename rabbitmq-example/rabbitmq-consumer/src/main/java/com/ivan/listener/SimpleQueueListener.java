package com.ivan.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Simple Queue（简单队列）
 * -- 不需要指定交换机，从指定队列获取消息
 *
 * @author: WB
 * @version: v1.0
 */
@Slf4j
@Component
public class SimpleQueueListener {

    @RabbitListener(queues = "simple.queue")
    public void listenerSimpleQueue1(String msg) {
        log.info("listener1 接收到简单队列【{}】消息内容为：{}", "simple.queue", msg);
    }

    @RabbitListener(queues = "simple.queue")
    public void listenerSimpleQueue2(String msg) {
        log.info("listener2 接收到简单队列【{}】消息内容为：{}", "simple.queue", msg);
    }

    @RabbitListener(queuesToDeclare = @Queue(name = "simple.queue"))
    public void listenerSimpleQueue3(String msg) {
        log.info("listener3 接收到简单队列【{}】消息内容为：{}", "simple.queue", msg);
    }

    @RabbitListener(queuesToDeclare = @Queue(name = "simple.queue.map"))
    public void listenerSimpleQueue4(Map<String, Object> msg) {
        log.info("listener4 接收到简单队列【{}】消息内容为：{}", "simple.queue.map", msg);
    }
}
