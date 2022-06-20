package com.ivan.test;

import com.ivan.redis.RedisExampleApplication;
import com.ivan.redis.utils.RedisCommon;
import com.ivan.redis.utils.RedisList;
import com.ivan.redis.utils.RedisString;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * @author: WB
 * @version: v1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisExampleApplication.class)
public class RedisListTest {

    @Autowired
    private RedisList redisList;
    @Autowired
    private RedisCommon redisCommon;

    @Test
    public void testPush() {
        String cacheKey = "redisList";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        for (int i = 0; i < 20; i++) {
            cal.add(Calendar.MINUTE, i);
            redisList.leftPush(cacheKey, format.format(cal.getTime()));
        }
    }

    @Test
    public void testGet() {
        String cacheKey = "redisList";

        Long length = redisList.length(cacheKey);
        if (length != null && length > 0) {
            List<String> rangeList = redisList.getRangeList(cacheKey, length - 1, length);
            rangeList.forEach(System.out::println);
        }
    }

    @Test
    public void testRightPop() {
        String cacheKey = "redisList";
        String val = redisList.rightPop(cacheKey);
        System.out.println(val);
    }


    @Test
    public void testGetAllList() {
        String cacheKey = "redisList";
        Long length = redisList.length(cacheKey);
        if (length != null && length > 0) {
            List<String> rangeList = redisList.getRangeList(cacheKey, 0, length);
            System.out.printf("列表长度：%s  查询列表长度：%s \n", length, rangeList.size());
            rangeList.forEach(System.out::println);
        }
    }
}
