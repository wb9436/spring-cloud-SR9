package com.ivan.redis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author: WB
 * @version: v1.0
 */
@Component
public class RedisSortSet {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 向有序集合中添加元素
     *
     * @param key      缓存key
     * @param scoreKey 分数key
     * @param score    分数
     * @return java.lang.Boolean 返回是否添加成功
     * @author WuBing
     * @date 2022-05-08 11:59:48
     */
    public Boolean put(String key, String scoreKey, double score) {
        return redisTemplate.opsForZSet().add(key, scoreKey, score);
    }

    /**
     * 从集合中获取成员分数
     *
     * @param key      缓存key
     * @param scoreKey 分数key
     * @return java.lang.Double 返回分数
     * @author WuBing
     * @date 2022-05-08 12:20:12
     */
    public Double getScore(String key, String scoreKey) {
        return redisTemplate.opsForZSet().score(key, scoreKey);
    }

    /**
     * 获取成员索引
     *
     * @param key      缓存key
     * @param scoreKey 分数key
     * @return java.lang.Long 返回成员对应索引
     * @author WuBing
     * @date 2022-05-08 12:25:39
     */
    public Long getIndex(String key, String scoreKey) {
        return redisTemplate.opsForZSet().rank(key, scoreKey);
    }


    /**
     * 根据分数获取指定范围成员列表（倒序）
     *
     * @param key      缓存key
     * @param minScore 最小分数
     * @param maxScore 最大分数
     * @return java.util.Set<java.lang.String>
     * @author WuBing
     * @date 2022-05-08 12:43:04
     */
    public List<String> getByScoreDesc(String key, int minScore, int maxScore) {
        Set<String> sets = redisTemplate.opsForZSet().reverseRangeByScore(key, minScore, maxScore);
        if (sets != null && sets.size() > 0) {
            return new ArrayList<>(sets);
        }
        return null;
    }

    /**
     * 根据分数获取指定范围成员列表（升序）
     *
     * @param key      缓存key
     * @param minScore 最小分数
     * @param maxScore 最大分数
     * @return java.util.Set<java.lang.String>
     * @author WuBing
     * @date 2022-05-08 12:43:04
     */
    public List<String> getByScoreAsc(String key, int minScore, int maxScore) {
        Set<String> sets = redisTemplate.opsForZSet().rangeByScore(key, minScore, maxScore);
        if (sets != null && sets.size() > 0) {
            return new ArrayList<>(sets);
        }
        return null;
    }

    public Set<ZSetOperations.TypedTuple<String>> getByScoreWithScoreDesc(String key, int minScore, int maxScore) {
        return redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, minScore, maxScore);
    }

    /**
     * 分页按照分数获得成员列表（倒序）
     *
     * @param key      缓存key
     * @param pageNum  当前页数
     * @param pageSize 每页成员数量
     * @return java.util.List<java.lang.String>
     * @author WuBing
     * @date 2022-05-08 13:28:52
     */
    public List<String> getPageByScoreDesc(String key, int pageNum, int pageSize) {
        int start = (pageNum - 1) * pageSize;
        Set<String> sets = redisTemplate.opsForZSet().reverseRangeByScore(key, -1, Double.MAX_VALUE, start, pageSize);
        if (sets != null && sets.size() > 0) {
            return new ArrayList<>(sets);
        }
        return null;
    }

    /**
     * 分页按照分数获得成员列表（带有分数，倒序）
     *
     * @param key      缓存key
     * @param pageNum  当前页数
     * @param pageSize 每页成员数量
     * @return java.util.List<java.lang.String>
     * @author WuBing
     * @date 2022-05-08 13:28:52
     */
    public Set<ZSetOperations.TypedTuple<String>> getPageByScoreDescWithScore(String key, int pageNum, int pageSize) {
        int start = (pageNum - 1) * pageSize;
        return redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, -1, Double.MAX_VALUE, start, pageSize);
    }



}
