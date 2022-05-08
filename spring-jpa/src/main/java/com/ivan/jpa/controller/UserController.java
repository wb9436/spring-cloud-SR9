package com.ivan.jpa.controller;

import com.ivan.jpa.entity.UserInfo;
import com.ivan.jpa.service.UserService;
import com.ivan.jpa.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TODO
 *
 * @author WuBing
 * @date 2022-05-08 18:12
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/type1/{id}")
    public UserInfo findType1ById(@PathVariable("id") long id) {
        long start = System.currentTimeMillis();
        UserInfo user = userService.findById(id);
        List<UserInfo> list = userService.findAll1();
        System.out.printf("type1 查询共耗时：%s ms \n", System.currentTimeMillis() - start);
        return user;
    }

    @RequestMapping("/type2/{id}")
    public UserInfoVo findType2ById(@PathVariable("id") long id) {
        long start = System.currentTimeMillis();
        UserInfoVo user = userService.findType2ById(id);
        System.out.printf("type2 查询共耗时：%s ms \n", System.currentTimeMillis() - start);
        return user;
    }

    @RequestMapping("/type3/{id}")
    public UserInfoVo findType3ById(@PathVariable("id") long id) {
        long start = System.currentTimeMillis();
        UserInfoVo user = userService.findType3ById(id);
        List<UserInfoVo> list = userService.findAll3();
        System.out.printf("type3 查询共耗时：%s ms \n", System.currentTimeMillis() - start);
        return user;
    }
}
