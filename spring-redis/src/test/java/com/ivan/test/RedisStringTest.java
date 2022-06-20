package com.ivan.test;

import com.ivan.redis.RedisExampleApplication;
import com.ivan.redis.utils.RedisCommon;
import com.ivan.redis.utils.RedisString;
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
public class RedisStringTest {

    @Autowired
    private RedisString redisString;
    @Autowired
    private RedisCommon redisCommon;

    @Test
    public void add() {
        redisString.set("str1", "str", 60);
    }

    @Test
    public void testIsExist() {
        Boolean isExist = redisCommon.isExist("fafa");
        System.out.println(isExist ? "存在" : "不存在");

        isExist = redisCommon.isExist("str1");
        System.out.println(isExist ? "存在" : "不存在");
    }

    @Test
    public void testTTL() {
        Long str1 = redisCommon.ttl("str1");
        System.out.println(str1);
    }

    @Test
    public void testDel() {
        Boolean str1 = redisCommon.delete("str1");
        System.out.println(str1);
    }

    @Test
    public void testIncr() {
        String incrKey = "incrKey";
        redisString.set("incrKey", "1");

        String val = redisString.get(incrKey);
        System.out.println(val);

        Long increment = redisString.increment(incrKey, -1);
        System.out.println(increment);

        val = redisString.get(incrKey);
        System.out.println(val);
    }

}
