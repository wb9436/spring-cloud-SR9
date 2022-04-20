package com.ivan;

import com.ivan.domain.User;
import com.ivan.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author: WB
 * @version: v1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisApplication.class)
public class MybatisTest {

    @Autowired
    private UserService userService;

    @Test
    public void testBoolean() {
        List<User> list = userService.findBySex(true);
        System.out.println(list != null ? list.size() : "查询失败");
    }
}
