package com.han.test.springboot_test3.other;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface YunXia {
    String value() default "";

    String name() default "";
}
