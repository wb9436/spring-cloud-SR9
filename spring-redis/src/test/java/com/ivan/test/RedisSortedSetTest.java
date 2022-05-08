package com.ivan.test;

import com.ivan.redis.RedisExampleApplication;
import com.ivan.redis.utils.RedisCommon;
import com.ivan.redis.utils.RedisSortSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @author: WB
 * @version: v1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisExampleApplication.class)
public class RedisSortedSetTest {

    @Autowired
    private RedisSortSet redisSortSet;
    @Autowired
    private RedisCommon redisCommon;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void testPut() {
        for (int i = 0; i < 100; i++) {
            redisSortSet.put("userScoreList", "100" + (i >= 10 ? i : "0" + i), i);
        }
    }

    @Test
    public void test() {
        String cacheKey = "userScoreList";
        List<String> list = redisSortSet.getByScoreDesc(cacheKey, 10, 25);
        if (list == null) return;
        list.forEach(key -> {
            Long index = redisSortSet.getIndex(cacheKey, key);
            Double score = redisSortSet.getScore(cacheKey, key);
            System.out.printf("key=%s index:%s score:%s \n", key, index, score);
        });
    }

    @Test
    public void test2() {
        String cacheKey = "userScoreList";
        List<String> list = redisSortSet.getByScoreAsc(cacheKey, 10, 25);
        if (list == null) return;
        list.forEach(key -> {
            Long index = redisSortSet.getIndex(cacheKey, key);
            Double score = redisSortSet.getScore(cacheKey, key);
            System.out.printf("key=%s index:%s score:%s \n", key, index, score);
        });
    }

    @Test
    public void test3() {
        String cacheKey = "userScoreList";
        Set<ZSetOperations.TypedTuple<String>> typedTuples = redisTemplate.opsForZSet().rangeByScoreWithScores(cacheKey, 10, 25);
        if (typedTuples == null) return;
        typedTuples.forEach(item -> {
            String key = item.getValue();
            Double score = item.getScore();
            System.out.printf("key=%s score:%s \n", key, score);
        });
    }

    @Test
    public void testPageDescNoScore() {
        String cacheKey = "userScoreList";
        List<String> list = redisSortSet.getPageByScoreDesc(cacheKey, 12, 10);
        if (list == null) return;
        list.forEach(key -> {
            System.out.printf("key=%s \n", key);
        });
    }

    @Test
    public void testPageDescWithScore() {
        String cacheKey = "userScoreList";
        Set<ZSetOperations.TypedTuple<String>> pgeWithScore = redisSortSet.getPageByScoreDescWithScore(cacheKey, 2, 10);
        if (pgeWithScore == null) return;
        pgeWithScore.forEach(key -> {
            System.out.printf("key=%s score=%s\n", key.getValue(), key.getScore());
        });
    }


    @Test
    public void testSaveLikeClick() {
        String cacheKey = "news";

        long curTime = System.currentTimeMillis();
        Random random = new Random();
        int userIdPrefix = 100000;
        for (int i = 0; i < 1000; i++) {
            redisSortSet.put(cacheKey, String.valueOf(userIdPrefix + i), curTime - random.nextInt(1000) * 1000);
        }
    }

    @Test
    public void printNewsList() {
        String cacheKey = "news";
        Set<ZSetOperations.TypedTuple<String>> pages = redisSortSet.getPageByScoreDescWithScore(cacheKey, 1, 20);
        if (pages == null) return;
        pages.forEach(item -> {
            System.out.printf("%s -> %s \n", item.getValue(), getDateStr(item.getScore().longValue()));
        });
    }

    public String getDateStr(long time) {
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

}
