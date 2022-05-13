package com.ivan.test;

import com.ivan.redis.RedisExampleApplication;
import com.ivan.redis.utils.RedisCommon;
import com.ivan.redis.utils.RedisHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: WB
 * @version: v1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisExampleApplication.class)
public class RedisHashTest {

    @Autowired
    private RedisHash redisHash;
    @Autowired
    private RedisCommon redisCommon;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void testPut() {
        for (int i = 0; i < 10; i++) {
            redisHash.put("hash", "a" + i, i + "");
        }
    }

    @Test
    public void testGet() {
        System.out.println(redisHash.get("hash", "a3"));
    }

    @Test
    public void testPutIfAbsent() {
//        System.out.println(redisHash.putIfAbsent("hash", "a1", "2"));
        System.out.println(redisHash.putIfAbsent("hash", "a10", "10"));
    }

    @Test
    public void testHashIncrement() {
//        Long newVal = redisHash.pushIncrement("hashIncr", "a0", 2);
//        System.out.println(newVal);
//
//        newVal = redisHash.pushIncrement("hashIncr", "a0", 2);
//        System.out.println(newVal);
//
//        newVal = redisHash.pushIncrement("hashIncr", "a0", -1);
//        System.out.println(newVal);

        Long newVal = redisHash.pushIncrement("hashIncr", "a0", -5);
        System.out.println(newVal);
    }

    @Test
    public void testHashIncrVal() {
//        Long val = redisHash.getIncrValue("hashIncr", "a0");
//        System.out.println(val);

        Long val = redisHash.getIncrValue2("hashIncr", "a0");
        System.out.println(val);
    }

    @Test
    public void testPutInt() {
        redisTemplate.opsForHash().put("hashInt", 1+"", 100+"");
    }
}
