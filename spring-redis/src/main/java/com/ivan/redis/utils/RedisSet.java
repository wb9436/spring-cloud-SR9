package com.ivan.redis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author: WB
 * @version: v1.0
 */
@Component
public class RedisSet {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 添加元素
     *
     * @param key    缓存key
     * @param values 值
     * @return void
     * @author WuBing
     * @date 2022-05-08 11:26:14
     */
    public Long put(String key, String... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    /**
     * 判断元素是否存在
     *
     * @param key   缓存key
     * @param value 值
     * @return java.lang.Boolean 返回是否存在
     * @author WuBing
     * @date 2022-05-08 11:28:17
     */
    public Boolean isExist(String key, String value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }
}
