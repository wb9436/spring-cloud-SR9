package com.ivan.test;

import com.ivan.redis.RedisExampleApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 使用redis队列测试抢红包
 *
 * @author WuBing
 * @date 2022-05-26 14:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisExampleApplication.class)
public class RedisRobRedPackageTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 添加红包
     */
    @Test
    public void testAddRedPackage() {
        int totalCoin = 0;
        String cacheKey = "red:package:rob";

        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int red = random.nextInt(100) + 1;
            totalCoin += red;

            redisTemplate.opsForList().rightPush(cacheKey, String.valueOf(red));
        }
        System.out.printf("cacheKey=%s totalCoin=%s\n", cacheKey, totalCoin);
    }

    @Test
    public void robRedPackage() throws Exception {
        //cacheKey=red:package:rob totalCoin=947
        String cacheKey = "red:package:rob";
        ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 0; i < 50; i++) {
            Callable<Integer> callable = new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    String str = redisTemplate.opsForList().leftPop(cacheKey);
                    if (str != null) {
                        return Integer.parseInt(str);
                    }
                    return -1;
                }
            };
            Future<Integer> submit = service.submit(callable);
            Integer integer = submit.get();
            if (integer == -1) {
                System.err.println("没抢到红包");
            } else {
                System.err.println("抢到红包金额为：" + integer);
            }
        }


    }
}
