package com.han.test.springboot_test3;

import com.han.test.springboot_test3.spring.rabbitmq.SpringConsumer;
import com.han.test.springboot_test3.spring.rabbitmq.SpringProducer;
import com.han.test.springboot_test3.utils.excel.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootTest3ApplicationTests {

    @Autowired
    SpringProducer springProducer;

    @Test
    public void contextLoads() {
        springProducer.send();
    }



}
