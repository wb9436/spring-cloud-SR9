package com.ivan.jpa.controller;

import com.ivan.jpa.entity.UserInfo;
import com.ivan.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/{id}")
    public UserInfo findById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

}
