package com.ivan.test;

import com.ivan.jpa.SpringJpaApplication;
import com.ivan.jpa.dao.UserInfoCustomDao;
import com.ivan.jpa.dao.UserInfoDefDao;
import com.ivan.jpa.entity.UserInfo;
import com.ivan.jpa.vo.UserInfoVo;
import com.ivan.jpa.vo.UserInfoVo2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * JPA测试
 *
 * @author WuBing
 * @date 2022-05-08 17:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringJpaApplication.class)
public class SpringJpaTest {

    @Autowired
    private UserInfoDefDao defaultDao;
    @Autowired
    private UserInfoCustomDao customDao;

    @Test
    public void testFindById() {
        UserInfo userInfo = defaultDao.findById(1L).orElse(null);
        System.out.println(userInfo);
    }

    @Test
    public void testFindById1() {
        UserInfo userInfo = customDao.findById(1);
        System.out.println(userInfo);
    }

//    @Test
//    public void testFindById2() {
//        UserInfo userInfo = customDao.findById2(1); //编译报错
//        System.out.println(userInfo);
//    }

    @Test
    public void testFindById3() {
        UserInfo userInfo = customDao.findById3(1);
        System.out.println(userInfo);
    }

    @Test
    public void testFindById4() {
        UserInfo userInfo = customDao.findById4(1);
        System.out.println(userInfo);
    }

    @Test
    public void testFindById5() {
        UserInfo userInfo = customDao.findById5(1);
        System.out.println(userInfo);
    }

//    @Test
//    public void testFindById6() {
//        List<UserInfo> list = customDao.findById6(1);
//        list.forEach(System.out::println);
//    }

//    @Test
//    public void testFindById7() {
//        Map<String, Object> result = customDao.findById7(1);
//        result.keySet().forEach(key -> {
//            System.out.printf("%s -> % \n", key, result.get(key));
//        });
//    }

    @Test
    public void testFindSpecifyColumn() {
        List<Object[]> specifyColumns = customDao.findSpecifyColumns(1);
        specifyColumns.forEach(item -> {
            List<Object> objects = Arrays.asList(item);
            objects.forEach(System.out::println);
        });
    }

    @Test
    public void testFindByPage() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<UserInfo> pages = customDao.findByPage(1, pageable);
        List<UserInfo> list = pages.getContent();
        list.forEach(System.out::println);
    }

    @Test
    public void testFindByPage2() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<UserInfoVo> pages = customDao.findByPage2(1, pageable);
        List<UserInfoVo> list = pages.getContent();
        list.stream().map(e -> {
            UserInfoVo2 userInfoVo2 = new UserInfoVo2();
            BeanUtils.copyProperties(userInfoVo2, e);
            return userInfoVo2;
        }).forEach(System.out::println);
    }

}
