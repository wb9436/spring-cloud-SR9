package com.ivan.netty;

import com.ivan.netty.client.NettyClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @author: WB
 * @version: v1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NettyClientTest {

    @Autowired
    private NettyClient nettyClient;

    @Test
    public void testSendMsg1() throws InterruptedException {
        TimeUnit.SECONDS.sleep(10);

        nettyClient.sendMsg("测试发送消息");

        TimeUnit.SECONDS.sleep(5);
    }
}
