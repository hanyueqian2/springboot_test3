package com.han.test.springboot_test3.config;


import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;
/**
 * 结束的时候执行
 * @author han
 *
 * 2019年4月15日
 */
@Component
public class MyDisposableBean implements DisposableBean {

    @Override
    public void destroy() {
        System.out.println("结束");
    }
}