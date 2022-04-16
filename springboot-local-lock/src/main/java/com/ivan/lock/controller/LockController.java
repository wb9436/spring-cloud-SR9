package com.ivan.lock.controller;

import com.ivan.lock.annotations.LocalLock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: WB
 * @version: v1.0
 */
@RestController
public class LockController {

    @LocalLock(key = "test:arg[0]")
    @GetMapping("/test")
    public String testLimiter(@RequestParam String token) {
        return "success - " + token;
    }

}
