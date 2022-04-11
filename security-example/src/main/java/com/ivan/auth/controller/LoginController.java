package com.ivan.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: WB
 * @version: v1.0
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login() {
        return "redirect:main.html";
    }

    @RequestMapping("/toMain")
    public String toMain() {
        return "redirect:main.html";
    }


}
