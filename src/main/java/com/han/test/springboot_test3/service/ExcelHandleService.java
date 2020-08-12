package com.han.test.springboot_test3.service;

import com.github.crab2died.ExcelUtils;
import com.han.test.springboot_test3.domain.FamilyBusinessControl;
import com.han.test.springboot_test3.mapper.FamilyBusinessControlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Excel处理相关服务
 */
@Service
public class ExcelHandleService {
    @Autowired
    FamilyBusinessControlMapper familyBusinessControlMapper;

    /**
     * Excel分析读取
     */
    public void excelForStock() {
        //亲属关系备注索引
        int detailIndex = 5;
        long startTime = System.currentTimeMillis();
        //xlsx文件路径
        String path = "D:\\wisdom\\myProject\\springboot_test3-master\\src\\main\\resources\\excel\\family_business_control.xlsx";

        //最终给算法的数据结构
        Map<String, Map<String, List<List<String>>>> finalGroup = new HashMap<>();

        List<List<String>> lists = null;
        try {
            // 不基于注解,将Excel内容读至List<List<String>>对象内
            lists = ExcelUtils.getInstance().readExcel2List(path, 2, 16412, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("读取Excel至String数组：");
        //首先根据股票号分组
        Map<String, List<List<String>>> bigGroup = lists.stream().collect(Collectors.groupingBy(data -> data.get(0)));

        //根据日期分组
        bigGroup.forEach((key, list) -> {
            Map<String, List<List<String>>> smallGroup = list.stream().collect(Collectors.groupingBy(d -> d.get(2)));
            finalGroup.put(key, smallGroup);
        });


        //1.首先填充所有的空缺数据,补充股东为空的数据,方便后面计算
        finalGroup.forEach((key, value) -> {
            value.forEach((childKey, childValue) -> {
                for (int i = 0; i < childValue.size(); i++) {
                    List<String> data = childValue.get(i);
                    //最后一个字符串不为空的索引
                    int lastIndex = selectLastIndex(data);
                    //当前索引,8是第一个持股人的索引
                    int currentIndex = 9;
                    //填充一行的所有数据
                    while (currentIndex < lastIndex) {
                        //向上查找数据
                        if ("".equals(data.get(currentIndex))) {
                            int j = i - 1;
                            while (true) {
                                if (j < 0) {
                                    break;
                                }
                                if (!"".equals(childValue.get(j).get(currentIndex))) {
                                    data.set(currentIndex, childValue.get(j).get(currentIndex));
                                    data.set(currentIndex - 1, childValue.get(j).get(currentIndex - 1));
                                    break;
                                }
                                j--;
                            }
                        }
                        currentIndex += 2;
                    }
                }
            });
        });

        //2.更新股东2-11的占比数据
        finalGroup.forEach((key, value) -> {
            value.forEach((childKey, childValue) -> {
                childValue.forEach(data -> {
                    //当前索引,8是第一个持股人的索引
                    int currentIndex = 11;
                    while (currentIndex <= data.size() && !"".equals(data.get(currentIndex))) {
                        String preStr = data.get(currentIndex - 2);
                        String currentStr = data.get(currentIndex);
                        BigDecimal preNum = BigDecimal.ZERO;
                        BigDecimal currentNum = BigDecimal.ZERO;
                        try {
                            preNum = new BigDecimal(preStr);
                            currentNum = new BigDecimal(currentStr);
                        } catch (NumberFormatException e) {
//                                e.printStackTrace();
                        }
                        data.set(currentIndex, currentNum.multiply(preNum).divide(new BigDecimal(100), 8, BigDecimal.ROUND_HALF_UP).toString());
                        currentIndex += 2;
                    }
                });
            });
        });

        //保存到数据库中的数据
        List<FamilyBusinessControl> resultData = new ArrayList<>();
        //3.筛选出亲属关系中出现的人名,并求和
        finalGroup.forEach((key, value) -> {
            value.forEach((childKey, childValue) -> {
                Map<String, String> tmpMap = new HashMap<>();
                childValue.forEach(data -> {
                    //最后一个字符串不为空的索引
                    int lastIndex = selectLastIndex(data);
                    if (data.get(detailIndex).contains(data.get(lastIndex - 1))) {
                        String name = data.get(lastIndex - 1);
                        String ratioStr = data.get(lastIndex);
                        if (tmpMap.get(name) == null) {
                            tmpMap.put(name, ratioStr);
                        } else {
                            String currentNumStr = tmpMap.get(name);
                            try {
                                String ratioNum = new BigDecimal(currentNumStr).add(new BigDecimal(ratioStr)).toString();
                                tmpMap.put(name, ratioNum);
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                final BigDecimal[] sum = {BigDecimal.ZERO};
                tmpMap.forEach((tmpKey, tmpValue) -> {
                    FamilyBusinessControl familyBusinessControl = new FamilyBusinessControl();
                    familyBusinessControl.setStockNum(key);
                    familyBusinessControl.setDateStr(childKey);
                    familyBusinessControl.setName(tmpKey);
                    familyBusinessControl.setEquityRatio(tmpValue);
                    resultData.add(familyBusinessControl);
                    try {
                        sum[0] = sum[0].add(new BigDecimal(tmpValue));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                });
                //增加求和数据, 每年股权求和
                if (BigDecimal.ZERO.compareTo(sum[0]) != 0) {
                    FamilyBusinessControl familyBusinessControl = new FamilyBusinessControl();
                    familyBusinessControl.setStockNum(key);
                    familyBusinessControl.setDateStr(childKey);
                    familyBusinessControl.setName("SUM");
                    familyBusinessControl.setEquityRatio(sum[0].toString());
                    resultData.add(familyBusinessControl);
                }
            });
        });

        //排序
        List<FamilyBusinessControl> list = resultData.stream()
                .sorted(Comparator.comparing(FamilyBusinessControl::getStockNum).thenComparing(FamilyBusinessControl::getDateStr))
                .collect(Collectors.toList());

        list.forEach(System.out::println);

        //4.保存数据库
        familyBusinessControlMapper.insertList(list);
        long endTime = System.currentTimeMillis();
        System.out.println("花费时间:" + (endTime - startTime));
    }


    /**
     * 查找最后一位不为空的索引
     * @return
     */
    public int selectLastIndex(List<String> list) {
        for (int i = list.size() - 1; i >= 0; i--) {
            if (!"".equals(list.get(i))) {
                return i;
            }
        }
        throw new RuntimeException("数据异常:" + list);
    }

}
