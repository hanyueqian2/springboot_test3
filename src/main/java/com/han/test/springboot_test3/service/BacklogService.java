package com.han.test.springboot_test3.service;

import com.han.test.springboot_test3.mapper.BacklogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BacklogService {
    @Autowired
    BacklogMapper backlogMapper;


}
