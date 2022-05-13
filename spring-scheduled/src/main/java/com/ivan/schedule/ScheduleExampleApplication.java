package com.ivan.schedule;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 测试定时任务
 *
 * @author WuBing
 * @date 2022-05-13 16:36
 */
@Slf4j(topic = "start_log")
@EnableScheduling
@SpringBootApplication
public class ScheduleExampleApplication {
    private static final Logger logger = LoggerFactory.getLogger("start_log");

    public static void main(String[] args) {
        log.info("测试定时任务--启动");
        logger.info("测试定时任务--启动");
        SpringApplication.run(ScheduleExampleApplication.class, args);
    }

}
