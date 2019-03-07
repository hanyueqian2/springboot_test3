package com.han.test.springboot_test3.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AutoRunService {
    private BacklogService name;

    public AutoRunService(BacklogService name) {
        this.name = name;
    }

    //定时执行
//    @Async
//    @Scheduled(cron = "0/2 * * * * ? ")
//    public void send() {
//        System.out.println("123456789");
//    }
}
