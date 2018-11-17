package com.han.test.springboot_test3;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyTest {
    private String name = "han";
    private Integer age = 15;
    private Long id;
}

class lombokTest{
    public static void main(String[] args) {
        MyTest myTest = new MyTest();
        System.out.println(myTest.getAge());
    }
}
