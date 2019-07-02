package com.han.test.springboot_test3;

import com.han.test.springboot_test3.spring.rabbitmq.SpringProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
