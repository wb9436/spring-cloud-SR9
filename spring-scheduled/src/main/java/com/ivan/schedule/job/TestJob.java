package com.ivan.schedule.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 测试任务
 *
 * @author WuBing
 * @date 2022-05-13 17:30
 */
@Slf4j
@Component
public class TestJob {

    /**
     * Long fixedDelay：定时任务每隔多久执行一次，单位是毫秒，上一次任务结束后开始计算下次执行的时间。
     */
    @Scheduled(fixedDelay = 10 * 1000)
    public void testFixedDelay() {
        log.info("TestJob testFixedDelay 每 10s 执行了一次！！！受结束时间影响，结束后开始计时！！！");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Long fixedRate：与fixedDelay一样表示定时任务的执行时间间隔，不同的是fixedRate的不会受到上一次任务结束时间的影响
     */
    @Scheduled(fixedRate = 10 * 1000)
    public void testFixedRate() {
        log.info("TestJob testFixedRate 每 10s 执行了一次！！！不受结束时间影响！！！");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Long initialDelay：项目启动后不马上执行定时器，根据initialDelay的值延时执行。
     */
    @Scheduled(initialDelay = 3 * 1000, fixedRate = 5 * 1000)
    public void testInitialDelay() {
        log.info("TestJob testInitialDelay 延迟 3s 执行！！！");
    }

    /**
     * 自定义表达式：每隔5s执行一次
     */
    @Scheduled(cron = "0/5 * * * * ? ")
    public void testCorn() {
        log.info("TestJob testCorn 每隔 5s 执行一次！！！");
    }

}
