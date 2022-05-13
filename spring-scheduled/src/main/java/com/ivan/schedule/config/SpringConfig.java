package com.ivan.schedule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * spring配置类
 *
 * @author WuBing
 * @date 2022-05-13 18:10
 */
@Configuration
public class SpringConfig {

//    //创建定时任务线程池
//    @Bean
//    public TaskScheduler taskScheduler() {
//        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
//        taskScheduler.setPoolSize(64);
//        return taskScheduler;
//    }

}
