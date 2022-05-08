package com.ivan.redis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author: WB
 * @version: v1.0
 */
@Component
public class RedisHash {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 添加元素
     *
     * @param key     缓存key
     * @param hashKey hashKey
     * @param value   值
     * @return void
     * @author WuBing
     * @date 2022-05-08 10:52:44
     */
    public void put(String key, String hashKey, String value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * 获取元素值
     *
     * @param key     缓存key
     * @param hashKey hashKey
     * @return java.lang.String
     * @author WuBing
     * @date 2022-05-08 10:56:15
     */
    public String get(String key, String hashKey) {
        return (String) redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 不存在则添加
     *
     * @param key     缓存key
     * @param hashKey hashKey
     * @param value   值
     * @return java.lang.Boolean 返回是否添加成功
     * @author WuBing
     * @date 2022-05-08 11:02:56
     */
    public Boolean putIfAbsent(String key, String hashKey, String value) {
        return redisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
    }


    /**
     * 按步长设置元素值
     *
     * @param key     缓存key
     * @param hashKey hashKey
     * @param offset  递增步长
     * @return java.lang.Long 返回递增后的值
     * @author WuBing
     * @date 2022-05-08 11:04:48
     */
    public Long pushIncrement(String key, String hashKey, int offset) {
        return redisTemplate.opsForHash().increment(key, hashKey, offset);
    }

    /**
     * 获取自增元素的值
     *
     * @param key     缓存key
     * @param hashKey hashKey
     * @return java.lang.Long 返回递增后的值
     * @author WuBing
     * @date 2022-05-08 11:17:56
     */
    public Long getIncrValue(String key, String hashKey) {
        return redisTemplate.opsForHash().increment(key, hashKey, 0);
    }

    /**
     * 获取自增元素的值2
     *
     * @param key     缓存key
     * @param hashKey hashKey
     * @return java.lang.Long 返回递增后的值
     * @author WuBing
     * @date 2022-05-08 11:17:56
     */
    public Long getIncrValue2(String key, String hashKey) {
        return Long.parseLong((String) Objects.requireNonNull(redisTemplate.opsForHash().get(key, hashKey)));
    }

    /**
     * 判断Hash元素是否存在
     *
     * @param key     缓存key
     * @param hashKey hashKey
     * @return java.lang.Boolean 返回是否存在
     * @author WuBing
     * @date 2022-05-08 11:19:12
     */
    public Boolean isExist(String key, String hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }
}
