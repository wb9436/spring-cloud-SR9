package com.ivan.redis.utils;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author: WB
 * @version: v1.0
 */
@Component
public class RedisCommon {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 缓存延期
     *
     * @param key     缓存key
     * @param timeout 超时时间
     * @param unit    单位
     * @return 是否操作成功
     */
    public Boolean expireTime(String key, int timeout, TimeUnit unit) {
        if (timeout > 0) {
            return redisTemplate.expire(key, timeout, unit);
        }
        return false;
    }

    /**
     * 删除key
     *
     * @param key 缓存key
     * @return 是否操作成功
     */
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 获取过期时间
     *
     * @param key 缓存key
     * @return 返回结果：-1（不过期）；-2（不存在key）；其他（剩余过期时间）
     */
    public Long ttl(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 判断缓存key是否存在
     *
     * @param key 缓存key
     * @return java.lang.Boolean 是否存在
     * @author WuBing
     * @date 2022-05-08 10:11:02
     */
    public Boolean isExist(String key) {
        return redisTemplate.hasKey(key);
    }


}
