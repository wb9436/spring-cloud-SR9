package com.ivan.redis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: WB
 * @version: v1.0
 */
@Component
public class RedisList {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 将元素插入队列尾部
     *
     * @param key   缓存key
     * @param value 值
     * @return void
     * @author WuBing
     * @date 2022-05-08 10:32:02
     */
    public void rightPush(String key, String value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 从队列尾部取出元素
     *
     * @param key 缓存key
     * @return java.lang.String 取值
     * @author WuBing
     * @date 2022-05-08 10:38:21
     */
    public String rightPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 将元素插入队列头部
     *
     * @param key   缓存key
     * @param value 值
     * @return void
     * @author WuBing
     * @date 2022-05-08 10:36:02
     */
    public void leftPush(String key, String value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 从队列头部取出元素
     *
     * @param key 缓存key
     * @return java.lang.String 返回值
     * @author WuBing
     * @date 2022-05-08 10:34:42
     */
    public String leftPop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 获取队列长度
     *
     * @param key 缓存key
     * @return java.lang.Long ：0(不存在)； >0(队列长度)
     * @author WuBing
     * @date 2022-05-08 10:40:19
     */
    public Long length(String key) {
        return redisTemplate.opsForList().size(key);
    }


    /**
     * 获取指定下标范围的列表
     *
     * @param key   缓存key
     * @param start 起始位置
     * @param end   结束位置
     * @return java.util.List<java.lang.String>
     * @author WuBing
     * @date 2022-06-08 22:04:33
     */
    public List<String> getRangeList(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

}
