package com.ivan.controller;

import com.ivan.entity.RequestEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: WB
 * @version: v1.0
 */
@Slf4j
@RestController
public class HelloController {

    @Value("${server.port}")
    private Integer serverPort;

    @GetMapping("/get")
    public String get(HttpServletRequest request) {
        log.info("Feign example provider：收到 GET 请求！！！serverPort={} token={}", serverPort, request.getHeader("token"));

        String name = request.getParameter("name");
        return "hello, world! GET request：" + name;
    }

    @PostMapping("/post")
    public String post(HttpServletRequest request) {
        log.info("Feign example provider：收到 POST 请求！！！serverPort=" + serverPort);

        String name = request.getParameter("name");
        return "hello, world! POST request：" + name;
    }

    @RequestMapping("/path/{name}")
    public String path(@PathVariable("name") String name) {
        log.info("Feign example provider：收到 GET PATH 请求！！！serverPort=" + serverPort);

        return "hello, world! PATH request：" + name;
    }

    @RequestMapping("/body")
    public String body(@RequestBody RequestEntity requestEntity) {
        log.info("Feign example provider：收到 REQUEST BODY 请求！！！serverPort=" + serverPort);

        return "hello, world! BODY request：" + requestEntity.getName();
    }
}
