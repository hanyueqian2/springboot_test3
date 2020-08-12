package com.han.test.springboot_test3.test;

import com.han.test.springboot_test3.service.RedisUtils;
import com.han.test.springboot_test3.service.arithmetic.AlleyTypeAndNum;
import com.han.test.springboot_test3.utils.excel.MessageUtils;
import com.han.test.springboot_test3.utils.excel.Student;
import com.han.test.springboot_test3.utils.excel.Teacher;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class EasyTest1 {
    @Autowired
    MessageUtils messageUtils;
    @Autowired
    RedisUtils redisUtils;









    @Test
    public void testA2(){
        long startTime = System.currentTimeMillis();
        Map<Integer, Integer> paramMap = new HashMap<>(20);
        paramMap.put(1, 5);
        paramMap.put(4, 5);
        paramMap.put(5, 8);
        paramMap.put(6, 7);
        paramMap.put(7, 5);
        paramMap.put(8, 5);
        paramMap.put(9, 6);
        testA1(6, 30, paramMap);
        System.out.println("时间: " + (System.currentTimeMillis() - startTime));
    }

    public int[] testA1(int needNum, int resultNUm, Map<Integer, Integer> paramMap) {
        int paramNum = 0;
        //1.缩减枚举元素
        for (Integer key : paramMap.keySet()) {
            Integer value = paramMap.get(key);
            if (value > needNum) {
                paramMap.put(key, needNum);
                paramNum += needNum;
            } else {
                paramNum += value;
            }
        }
        //2.穷举数组
        int[] tmpParam = new int[paramNum];
        int index = 0;
        for (Integer key : paramMap.keySet()) {
            for (int i = 0; i < paramMap.get(key); i++) {
                tmpParam[index++] = key;
            }
        }

        System.out.println("穷举中数量:" + tmpParam.length);
        //3.穷举过程
        int[] resultList = selectAllParam(needNum, resultNUm, tmpParam);
        while (resultList == null && needNum > 1) {
            resultList = selectAllParam(--needNum, resultNUm, tmpParam);
        }
        for (int i = 0; i < resultList.length; i++) {
            System.out.println(resultList[i]);
        }
        return resultList;

    }


    public int[] selectAllParam(int needNum, int resultNum, int[] allParam) {
        int[] indexList = new int[needNum];

        //初始化
        for (int i = 0; i < needNum; i++) {
            indexList[i] = i;
        }

        int index = needNum - 1;
        while (index > 1) {
            int length = allParam.length;
            index = needNum - 1;
            //一次枚举
            while (index > 1) {
                if (indexList[index] >= length - 1
                        || (index != needNum - 1 && indexList[index] >= indexList[index+1] - 1)) {
                    index--;
                } else {
                    indexList[index]++;
                    while (index < needNum -1) {
                        indexList[index + 1] = indexList[index] + 1;
                        index++;
                    }
                    break;
                }
            }
            int sum = 0;
            for (int i = 0; i < needNum; i++) {
                sum += allParam[indexList[i]];
            }
            if (sum == resultNum) {
                System.out.println("---");
//                break;
            }
        }

        int[] resultList = new int[indexList.length];
        for (int i = 0; i < indexList.length; i++) {
            resultList[i] = allParam[indexList[i]];
        }

        return resultList;
    }


}
