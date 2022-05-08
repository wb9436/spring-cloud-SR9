package com.ivan.redis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author: WB
 * @version: v1.0
 */
@Component
public class RedisBitMap {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * BitMap设置对应偏移量值
     *
     * @param key    缓存key
     * @param offset 偏移量
     * @return void
     * @author WuBing
     * @date 2022-05-08 14:04:45
     */
    public void put(String key, long offset) {
        redisTemplate.opsForValue().setBit(key, offset, true);
    }

    /**
     * 取出BitMap偏移量的值
     *
     * @param key    缓存key
     * @param offset 偏移量
     * @return java.lang.Boolean 返回是否有值
     * @author WuBing
     * @date 2022-05-08 14:06:29
     */
    public Boolean get(String key, long offset) {
        return redisTemplate.opsForValue().getBit(key, offset);
    }


}
