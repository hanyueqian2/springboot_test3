package com.han.test.springboot_test3;

import com.han.test.springboot_test3.domain.ClassA;
import com.han.test.springboot_test3.utils.excel.Student;
import org.junit.Test;

import java.util.*;

public class EasyTest {

    @Test
    public void testSet() {

        Set set = new HashSet<String>();
        Student student = new Student();
        set.add(new Student());
        set.add(student);
        set.add(student);
        System.out.println(set);

        TreeSet<Object> treeSet = new TreeSet<>();

    }

    @Test
    public void testMap() {
        Map map = new LinkedHashMap();
        map.put(null, null);
        map.put("1", "hello");
        map.put("2", 555);
        map.put("3", new Student());

        System.out.print(map.get("1"));
    }
}
