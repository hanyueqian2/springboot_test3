package com.han.test.springboot_test3;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Fraction {

    private Fraction c;
    private List<Fraction> list;

    public void add(Fraction newFraction){
        list.add(newFraction);
    }

    //提取List中的某一个对象
    public Fraction getOne(int index){
        return list.get(index);
    }

    public Fraction getC() {
        return c;
    }

    public void setC(Fraction c) {
        this.c = c;
    }
}

