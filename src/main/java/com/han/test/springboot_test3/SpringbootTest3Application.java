package com.han.test.springboot_test3;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@MapperScan("com.han.test.springboot_test3.mapper")
@EnableScheduling
@Slf4j
public class SpringbootTest3Application {

    public static void main(String[] args) {
        SpringApplication.run(com.han.test.springboot_test3.SpringbootTest3Application.class, args);
        System.out.println("spring启动成功");
    }
}
// (module,businessNumber,task,leaveTime,schedule,enter)
//(backlog.module,backlog.businessNumber,backlog.task,backlog.leaveTime,backlog.schedule,backlog.enter)