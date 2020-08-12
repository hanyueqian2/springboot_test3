package com.han.test.springboot_test3.test;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class TestVolatile {
    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            ThreadDemo threadDemo = new ThreadDemo();
            new Thread(threadDemo).start();
        }


    }
}

class ThreadDemo implements Runnable {

    private static CopyOnWriteArrayList list = new CopyOnWriteArrayList<>();

    static {
        list.add("A");
        list.add("B");
        list.add("C");
    }
    @Override
    public void run() {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            list.add("DDD");
        }
    }
}
