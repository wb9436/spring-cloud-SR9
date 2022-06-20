package com.ivan.redis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author: WB
 * @version: v1.0
 */
@Component
public class RedisString {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private RedisCommon redisCommon;

    /**
     * 添加缓存
     *
     * @param key   缓存key
     * @param value 值
     */
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 获取缓存
     *
     * @param key 缓存key
     * @return
     */
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 添加缓存
     *
     * @param key     缓存key
     * @param value   值
     * @param seconds 有效时间（秒）
     */
    public void set(String key, String value, int seconds) {
        redisTemplate.opsForValue().set(key, value);
        redisCommon.expireTime(key, seconds, TimeUnit.SECONDS);
    }

    /**
     * 不存在则添加
     *
     * @param key   缓存key
     * @param value 值
     * @return 是否设置成功
     */
    public Boolean setIfAbsent(String key, String value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    /**
     * 不存在则添加
     *
     * @param key     缓存key
     * @param value   值
     * @param seconds 有效时间（秒）
     * @return 是否设置成功
     */
    public Boolean setIfAbsent(String key, String value, int seconds) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    /**
     * 设置自增key
     *
     * @param key    缓存key
     * @param offset 偏移量（递增步长）
     * @return 返回增加后的值
     */
    public Long increment(String key, int offset) {
        return redisTemplate.opsForValue().increment(key, offset);
    }


}
