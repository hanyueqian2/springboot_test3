package com.han.test.springboot_test3.soap;

public enum Position {
    FORWARD("小前锋"),
    SHOOTING("控球後衛");
    String value;
    Position(String value) {
        this.value = value;
    }
    public String value() {
        return value;
    }
    public static Position fromValue(String v) {
        for (Position c : Position.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}

