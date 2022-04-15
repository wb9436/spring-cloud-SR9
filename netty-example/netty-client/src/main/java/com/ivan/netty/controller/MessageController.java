package com.ivan.netty.controller;

import com.ivan.netty.client.NettyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: WB
 * @version: v1.0
 */
@RestController
public class MessageController {

    @Autowired
    private NettyClient nettyClient;

    @RequestMapping("/send")
    public String sendMsg(@RequestParam String msg) {
        try {
            nettyClient.sendMsg(msg);
            return "发送成功！！！";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "发送失败！！！";
    }

}
