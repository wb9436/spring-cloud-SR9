package com.ivan.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: WB
 * @version: v1.0
 */
@RestController
@RefreshScope
public class HelloController {

    @Value(value = "${user.address}")
    private String address;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello，" + address;
    }

}
