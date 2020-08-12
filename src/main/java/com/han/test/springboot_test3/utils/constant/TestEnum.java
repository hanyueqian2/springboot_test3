package com.han.test.springboot_test3.utils.constant;


public enum TestEnum {
    ADD("+") {
        public int exec(int a,int b) {
            return a + b;
        }
    },
    SUB("-") {
        public int exec(int a, int b) {
            return a - b;
        }
    };

    String value;

    public String getValue() {
        return value;
    }

    TestEnum(String value) {
        this.value = value;
    }

    public abstract int exec (int a,int b);
}
