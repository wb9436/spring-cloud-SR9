package com.ivan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 实现访问限流
 *
 * @author: WB
 * @version: v1.0
 */
@SpringBootApplication
public class SpringLimiterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringLimiterApplication.class, args);
    }

}
