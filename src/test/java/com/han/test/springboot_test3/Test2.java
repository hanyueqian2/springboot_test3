package com.han.test.springboot_test3;

import com.han.test.springboot_test3.other.YunXia;
import jdk.nashorn.tools.Shell;
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
}
/**
 *内存占位符对象，一个OOMObject大约占64KB
 */
class OOMObject{
    public byte[] placeholder=new byte[64*1024];

    public static void fillHeap(int num)throws InterruptedException{
        List<OOMObject> list=new ArrayList<>();
        for(int i=0;i<num;i++){
            //稍作延时，令监视曲线的变化更加明显
            Thread.sleep(50);
            list.add(new OOMObject());
        }

    }
    public static void main(String[]args)throws Exception{
        Thread.sleep(5000);
        fillHeap(2000);
        System.gc();
        Thread.sleep(5000);
    }


}