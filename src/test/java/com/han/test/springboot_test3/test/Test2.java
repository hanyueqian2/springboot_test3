package com.han.test.springboot_test3.test;

import com.han.test.springboot_test3.service.arithmetic.AlleyTypeAndNum;
import com.han.test.springboot_test3.utils.excel.Student;
import org.apache.ibatis.cache.impl.PerpetualCache;
import org.junit.Test;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

@Valid
public class Test2 {
    /**
     * 测试JConsole工具的使用
     */
//    @Test
//    public void testJConsole(){
//        helloInterface();
//    }

    /**
     * 注解测试
     */
//    @YunXia
//    private void helloInterface() {
//        System.out.println("hello Interface");
//    }

    @Test
    public void testObject(){
        Object list = new ArrayList<>();
        Object temp = new PerpetualCache("");
        synchronized (this){

        }
    }

    @Test
    public void testString(){
        String coox = "012345";
        while (true) {
            if ("0".equals(coox.substring(0, 1))) {
                coox = coox.substring(1);
            } else {
                break;
            }
        }
        System.out.println(coox);
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
    public void testUUID(){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "").substring(0,32);
        String uuid2 = UUID.randomUUID().toString().replace("-", "");
        System.out.println(uuid);
        System.out.println(uuid2);
        System.out.println(UUID.randomUUID().toString());
        System.out.println(uuid.length());
    }

    @Test
    public void testTime(){
        DateFormat.getDateInstance().setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Date gmt = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo")).getTime();
        System.out.println(gmt.toLocaleString());
        System.out.println(new Date().toLocaleString());
        System.out.println(DateFormat.getDateInstance().format(new Date()));
        System.out.println(new Date());
    }

    TestThread myThread = new TestThread();
    Thread testThread;
    @Test
    public void testThread() {
        testThread = new Thread(myThread, UUID.randomUUID().toString());
        testThread.start();

        try {
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        testThread.interrupt();
        System.out.println(testThread);
        System.out.println(Thread.currentThread());
//        while (true) {
//            try {
//                System.out.println(testThread.isInterrupted());
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        try {
            Thread.sleep(1000 * 30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testThreads() {
        testThread.start();
        System.out.println(testThread);
        System.out.println(Thread.currentThread());
        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        testThread.interrupt();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testByteToString(){
        //,31,75,-45,21
        byte[] a = {12,45,86};
        String s = null;
        try {
            System.out.println(new String(a, "ISO-8859-1"));
            System.out.println(new String(a, "UTF-8"));
            System.out.println(new String(a, "UTF-16"));
            System.out.println(new String(a, "UTF-16LE"));
            System.out.println(new String(a, "UTF-16BE"));
            System.out.println(new String(a, "US-ASCII"));
            System.out.println(new String(a, "GBK"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
        
        String str = "你好,小红!";
        byte[] info = str.getBytes();
        for (byte b : info) {
            System.out.println(b);
        }
        System.out.println(new String(info));
    }

    @Test
    public void testFile(){
        System.out.println(new Date().toLocaleString());
    }
    
    @Test
    public void testClass(){
        try {
            Field name = Student.class.getDeclaredField("name");
            if (name.getType().equals(String.class)) {
                System.out.println(name);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRandom(){
        System.out.println(new Random().nextInt(1));
    }

    @Test
    public void testSome() {
        Father father = new Father();
        Son son = new Son();
        HashMap map = new HashMap();
        son.doSome(map);
    }
    
    @Test
    public void testString2(){
        ArrayList<String> list = new ArrayList<>();
        list.add("111");
        list.add("222");
        list.add("333");
        Stream<String> stream = list.stream();
        System.out.println("");
    }

    @Test
    public void testTwo(){
        for (int i = 1; i <= 30; i++) {
            System.out.println("当总数为" + i + "的时候----------------------");
            testOne(i);
        }
    }
    @Test
    public void testThree(){
        testOne(29);
    }


    /**
     *
     * @param sum 总储位数
     */
    //    @Test
    public void testOne(int sum){
        int[] h = new int[]{5, 4, 3, 1};
//        int[] h = new int[]{4, 3, 1};
//        int[] h = new int[]{5, 3, 1};
//        int[] h = new int[]{5, 4, 1};
//        int[] h = new int[]{5, 4, 3};
//        int[] h = new int[]{1};
        //每种巷道的数量
        int[] a = null;
//        int sum = 12;

        //总巷道数
        int nums = sum / h[0];
        int excess = sum % h[0];
        if (excess != 0) {
            nums += 1;
        }

        //设置最大深度巷道数量为 总巷道数 - 1
        for (int i = 0; i < h.length; i++) {
            int[] tmpA = new int[]{0, 0, 0, 0};
            tmpA[0] = nums - 1;
            tmpA[i] += 1;
            int tmpSum = 0;
            for (int j = 0; j < h.length; j++) {
                tmpSum += tmpA[j] * h[j];
            }
            if (tmpSum == sum) {
                a = tmpA;
                break;
            }
        }

        //设置最大深度巷道数量为 总巷道数 - 2
        if (a == null && nums > 1) {
            for (int i = 2; i < h.length; i++) {
                int[] tmpA = new int[]{0, 0, 0, 0};
                tmpA[0] = nums - 2;
                tmpA[1] = 1;
                tmpA[i] = 1;
                int tmpSum = 0;
                for (int j = 0; j < h.length; j++) {
                    tmpSum += tmpA[j] * h[j];
                }
                if (tmpSum == sum) {
                    a = tmpA;
                    break;
                }
            }
        }

        //当没有正好放下的情况时
        if (a == null) {
            int m = sum / h[0];
            int n = sum % h[0];
            a = new int[]{m, 0, 0, 0};
            //TODO 检查m是否大于现有的巷道数

            for (int i = 0; i < h.length; i++) {
                if (n <= h[h.length - i - 1]) {
                    a[h.length - i - 1] += 1;
                    break;
                }
            }

        }

        for (int i = 0; i < a.length; i++) {
            if (a[i] != 0) {
                System.out.println("深度为" + h[i] + "的巷道需要的数量为:" + a[i]);
            }
        }

    }

    @Test
    public void testSort(){
        HashMap<Integer, Integer> alleyTypeAndNumMap = new HashMap<>();
        alleyTypeAndNumMap.put(3, 8);
        alleyTypeAndNumMap.put(4, 15);
        alleyTypeAndNumMap.put(5, 12);
        alleyTypeAndNumMap.put(1, 23);
        List<AlleyTypeAndNum> alleyTypeAndNums = new ArrayList<>();
        Set<Integer> keys = alleyTypeAndNumMap.keySet();
        while (keys.size() > 0) {
            AlleyTypeAndNum alleyTypeAndNum = new AlleyTypeAndNum();
            alleyTypeAndNum.setStorageLocCount(0);
            Integer removeKey = null;
            for (Integer key : keys) {
                if (key > alleyTypeAndNum.getStorageLocCount()) {
                    alleyTypeAndNum.setStorageLocCount(key);
                    alleyTypeAndNum.setNum(alleyTypeAndNumMap.get(key));
                    removeKey = key;
                }
            }
            alleyTypeAndNums.add(alleyTypeAndNum);
            keys.remove(removeKey);
        }
        System.out.println( alleyTypeAndNums);
    }



}
class Father {
    public void doSome(Map map) {
        System.out.println("父类执行了" + map);
    }
}

class Son extends Father {
    public void doSome(HashMap map) {
        System.out.println("子类执行了" + map);
    }
}

class TestThread implements Runnable {

    protected AtomicBoolean waitLock = new AtomicBoolean(false);
    @Override
    public void run() {
        while(true){
            System.out.println(UUID.randomUUID().toString() + Thread.currentThread().getName() + Thread.currentThread().isInterrupted());
            try {
                Thread.sleep(1000);
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("终端成功");
                    break;
                }
            } catch (InterruptedException e) {
                System.out.println("线程已终止成功");
                break;
            }
        }
    }

    public AtomicBoolean getWaitLock() {
        return waitLock;
    }

}

