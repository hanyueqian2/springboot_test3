package com.han.test.springboot_test3.spring.rabbitmq;

import com.han.test.springboot_test3.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = RabbitConfig.QUEUE_B)
public class SpringConsumer2 {

//    @RabbitHandler
//    public void process(String msg) {
//        System.out.println("Receive2: " + msg);
//    }
}
