package com.ivan.reddis;

import com.ivan.netty.redis.RedisClient;
import org.junit.Test;

import java.io.IOException;

/**
 * @author: WB
 * @version: v1.0
 */
public class NettyRedisTest {

    @Test
    public void test1() throws IOException {
        RedisClient redisClient = new RedisClient("127.0.0.1", 6379);
        redisClient.start();

        redisClient.send("set username zhangsan");

        System.in.read();
    }
}
