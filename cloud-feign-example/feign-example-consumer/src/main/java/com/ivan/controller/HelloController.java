package com.ivan.controller;

import com.ivan.entity.RequestEntity;
import com.ivan.service.FeignProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: WB
 * @version: v1.0
 */
@RestController
public class HelloController {

    @Autowired
    private FeignProviderService feignProviderService;

    @RequestMapping("/get")
    public String get(HttpServletRequest request) {
        String name = request.getParameter("name");
        return feignProviderService.get(name);
    }

    @RequestMapping("/post")
    public String post(HttpServletRequest request) {
        String name = request.getParameter("name");
        return feignProviderService.post(name);
    }

    @RequestMapping("/path/{name}")
    public String path(@PathVariable("name") String name) {
        return feignProviderService.path(name);
    }

    @RequestMapping("/body")
    public String body() {
        return feignProviderService.body(new RequestEntity(131411, "王朝"));
    }

}
