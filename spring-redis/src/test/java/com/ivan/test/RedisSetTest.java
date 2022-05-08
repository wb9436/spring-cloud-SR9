package com.ivan.test;

import com.ivan.redis.RedisExampleApplication;
import com.ivan.redis.utils.RedisCommon;
import com.ivan.redis.utils.RedisSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: WB
 * @version: v1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisExampleApplication.class)
public class RedisSetTest {

    @Autowired
    private RedisSet redisSet;
    @Autowired
    private RedisCommon redisCommon;

    @Test
    public void testPut() {
        for (int i = 0; i < 10; i++) {
            redisSet.put("set", "a" + i, "b" + i);
        }
    }

    @Test
    public void testPutIfAbsent() {
        Long val = redisSet.put("set", "a1");
        System.out.println(val);
    }
}
