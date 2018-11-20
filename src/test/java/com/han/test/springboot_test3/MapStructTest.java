package com.han.test.springboot_test3;

import com.han.test.springboot_test3.controller.mapstruct.BacklogMapStruct;
import com.han.test.springboot_test3.domain.Backlog;
import com.han.test.springboot_test3.domain.dto.BacklogDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapStructTest {

    @Autowired
    BacklogMapStruct mapStruct;

    @Test
    public void test1(){
        Backlog backlog = new Backlog();
        backlog.setId(123L);
        backlog.setBusinessNumber("123");
        backlog.setLeaveTime(12314543L);
        System.out.println(mapStruct);


        BacklogDto dto = mapStruct.toDto(backlog);
//        BacklogDto dto = mapStruct.INSTANCE.toDto(backlog);


        System.out.println(dto);
    }
}

