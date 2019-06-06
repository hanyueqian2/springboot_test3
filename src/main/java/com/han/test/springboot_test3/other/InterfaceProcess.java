package com.han.test.springboot_test3.other;

import com.google.auto.service.AutoService;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * @author han
 */
@AutoService(Processor.class)
public class InterfaceProcess extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("注解开始-------------------------");
        for (TypeElement annotation : annotations) {
            System.out.println(annotation.toString());
        }
        System.out.println(roundEnv.toString());

        return false;
    }
}
