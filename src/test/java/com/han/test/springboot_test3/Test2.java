package com.han.test.springboot_test3;

import com.han.test.springboot_test3.other.YunXia;
import org.apache.ibatis.cache.impl.PerpetualCache;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Valid
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

    @Test
    public void testString(){
        String str = "  ";
        boolean notBlank = StringUtils.isNotBlank(str);
        hello(null);
    }

    public void hello(@NotNull String str) {
        System.out.println(str);
    }
    
    @Test
    public void testForeach() {
        List<String> strs = new ArrayList<>();
        for (String str : strs) {
            System.out.println(str);
        }
    }

    @Test
    public void testBerCode(){
        String testX = "20.14";
        String testY = "123.2";
        String x = "";
        String y = "";

        x = merge(testX);
        y = merge(testY);

        System.out.println(x);
        System.out.println(y);
    }

    private String merge(String num) {
        String x = "";
        String[] split = num.split("\\.");
        x += addAfter(split[0]);
        if (split.length == 1) {
            x += "000";
        } else {
            x += addBefore(split[1]);
        }
        return x;
    }


    private String addAfter(String num) {
        if (num.length() == 1) {
            num = "00" + num;
        } else if (num.length() == 2) {
            num = "0" + num;
        } else if (num.length() == 0) {
            num = "000";
        }
        return num;
    }
    private String addBefore(String num) {
        if (num.length() == 1) {
            num = num + "00";
        } else if (num.length() == 2) {
            num = num + "0";
        } else if (num.length() == 0) {
            num = "000";
        }
        return num;
    }

}
