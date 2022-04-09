package com.ivan;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author: WB
 * @version: v1.0
 */
@SpringBootApplication
public class RabbitProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitProviderApplication.class, args);
    }

    /*配置Rabbit的消息转换器*/
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
