package com.ivan.test;

import com.ivan.redis.RedisExampleApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Redis事务测试
 *
 * @author WuBing
 * @date 2022-05-26 14:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisExampleApplication.class)
public class RedisTransactionTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void testTransaction() {
        List<String> list = new ArrayList<>();
        list.add("100");
        list.add("101");
        list.add("99");

        List<Object> result = (List<Object>) redisTemplate.execute(new SessionCallback<Object>() {
            @Override
            public List<Object> execute(RedisOperations operations) throws DataAccessException {
                operations.multi();

                String unisn = Long.toString(System.currentTimeMillis());
                //记录红包拆分份数列表
                String redPackageShareListKey = "red:package:list:" + unisn;
                operations.opsForList().rightPushAll(redPackageShareListKey, list);
                operations.expire(redPackageShareListKey, 7, TimeUnit.DAYS);
                //红包详情
                String redPackageInfoKey = "red:package:info:" + unisn;
                Map<String, String> redPackageInfoMap = new HashMap<>();
                redPackageInfoMap.put("totalCoin", "300");
                redPackageInfoMap.put("peopleNum", "3");
                operations.opsForHash().putAll(redPackageInfoKey, redPackageInfoMap);
                operations.expire(redPackageInfoKey, 7, TimeUnit.DAYS);

                return operations.exec();
            }
        });
        result.forEach(System.out::println);
    }
}
