package com.han.test.springboot_test3.spring.rabbitmq;

import com.han.test.springboot_test3.config.RabbitConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 与springboot整合的生产者
 */
@Component
public class SpringProducer {
    /**
     * 通过注入AmqpTemplate接口的实例来实现消息的发送
     */
    @Autowired
    AmqpTemplate amqpTemplate;

    public void send() {
//        String msg = "Time:" + new Date();
//        System.out.println("send => " + msg);
//        amqpTemplate.convertAndSend(RabbitConfig.ROUTINGKEY_A, msg);
    }
}
