package com.han.test.springboot_test3;

import com.han.test.springboot_test3.other.YunXia;
import jdk.nashorn.tools.Shell;
import org.apache.ibatis.cache.impl.PerpetualCache;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Test2 {
    /**
     * 测试JConsole工具的使用
     */
    @Test
    public void testJConsole(){
        helloInterface();
    }

    /**
     * 注解测试
     */
    @YunXia
    private void helloInterface() {
        System.out.println("hello Interface");
    }

    @Test
    public void testObject(){
        Object list = new ArrayList<>();
        Object temp = new PerpetualCache("");
        synchronized (this){

        }
    }
}
