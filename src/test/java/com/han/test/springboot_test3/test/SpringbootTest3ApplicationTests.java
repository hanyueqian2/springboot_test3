package com.han.test.springboot_test3.test;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.han.test.springboot_test3.domain.Backlog;
import com.han.test.springboot_test3.mapper.BacklogMapper;
import com.han.test.springboot_test3.spring.rabbitmq.SpringProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(rollbackFor = Exception.class)
public class SpringbootTest3ApplicationTests {

    @Autowired
    SpringProducer springProducer;

    @Test
    public void contextLoads() {
        springProducer.send();
    }

    @Autowired
    BacklogMapper backlogMapper;
    @Test
    public void testSQL(){
        Backlog backlog = new Backlog();
        backlog.setId(8L);
        backlog.setTestNum(10);
        int i = backlogMapper.updateByPrimaryKeySelective(backlog);
        System.out.println(i);

//        List<Backlog> backlogs = backlogMapper.selectMany(10);
//        System.out.println(backlogs.size());

    }


}
