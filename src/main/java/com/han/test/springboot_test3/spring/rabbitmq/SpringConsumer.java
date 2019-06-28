package com.han.test.springboot_test3.spring.rabbitmq;

import com.han.test.springboot_test3.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 与springboot整合的消费者
 * 对queue1队列的监听
 * @author han
 */
@Component
@RabbitListener(queues = RabbitConfig.QUEUE_A)
public class SpringConsumer {

    /**
     * 将方法标记为Rabbit消息侦听器的目标的注释。
     * @param str 收到的消息
     */
//    @RabbitHandler
//    public void process(String str) {
//        System.out.println("Receiver : " + str);
//    }
}
