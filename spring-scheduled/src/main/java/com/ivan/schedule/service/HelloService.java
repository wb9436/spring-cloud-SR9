package com.ivan.schedule.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author WuBing
 * @date 2022-05-13 17:15
 */
@Service
@Slf4j(topic = "hello_log")
public class HelloService {
    private static final Logger logger = LoggerFactory.getLogger("hello_log");

    public void sayHello() {
        log.info("log sayHello -> {}", "你好啊！");
        logger.info("logger sayHello -> {}", "你好啊！");
    }
}
