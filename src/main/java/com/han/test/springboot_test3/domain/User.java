package com.han.test.springboot_test3.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class User {
    private Long userId;
    private String username;
    private String email;
    private Date gmtCreate;
}
