package com.ivan.schedule.controller;

import com.ivan.schedule.service.HelloService;
import com.ivan.schedule.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloController
 *
 * @author WuBing
 * @date 2022-05-13 17:11
 */
@Slf4j(topic = "hello_log")
@RestController
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger("hello_log");

    @Autowired
    private HelloService helloService;

    @RequestMapping("hello")
    public Result hello() {
        log.info("log 首页请求：methodName -> {}", "hello");
        logger.info("logger 首页请求：methodName -> {}", "hello");
        helloService.sayHello();
        return Result.success();
    }


}
