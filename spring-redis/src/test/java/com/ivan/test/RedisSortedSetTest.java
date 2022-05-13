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
import java.util.*;
import java.util.stream.Collectors;

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

    @Test
    public void testInitCharmPriceCache() {
        List<CharmPrice> list = CharmPrice.list;
        String cacheKey = "charm_price";
        String hashKey = "%s_%s"; // charmLevel_price

        list.forEach(item -> {
            String sortKey = String.format(hashKey, item.getCharmLevel(), item.getPrice());
            redisSortSet.put(cacheKey, sortKey, item.getCharmLevel());
        });
    }

    @Test
    public void testGetCharmPrice() {
        String cacheKey = "charm_price";
        List<String> list = redisSortSet.getByScoreAsc(cacheKey, 0, 6);
        if (list == null || list.isEmpty()) return;
        list.forEach(System.out::println);
        List<CharmPrice> charmPriceList = list.stream().map(item -> {
            String[] strings = item.split("_");
            return new CharmPrice(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]));
        }).distinct().collect(Collectors.toList());
        charmPriceList.forEach(System.out::println);
    }

    private static class CharmPrice {
        public static final List<CharmPrice> list = new ArrayList<>();

        static {
            list.add(new CharmPrice(0, 200));
            list.add(new CharmPrice(0, 400));
            list.add(new CharmPrice(1, 500));
            list.add(new CharmPrice(2, 600));
            list.add(new CharmPrice(2, 700));
            list.add(new CharmPrice(3, 800));
            list.add(new CharmPrice(4, 900));
            list.add(new CharmPrice(5, 1000));
            list.add(new CharmPrice(6, 1200));
            list.add(new CharmPrice(7, 1500));
            list.add(new CharmPrice(7, 2000));
            list.add(new CharmPrice(8, 2000));
        }

        private int charmLevel;
        private int price;

        public CharmPrice(int charmLevel, int price) {
            this.charmLevel = charmLevel;
            this.price = price;
        }

        public int getCharmLevel() {
            return charmLevel;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return "CharmPrice{" +
                    "charmLevel=" + charmLevel +
                    ", price=" + price +
                    '}';
        }
    }


}


