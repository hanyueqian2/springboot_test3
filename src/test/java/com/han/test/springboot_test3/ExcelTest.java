package com.han.test.springboot_test3;


import com.github.crab2died.ExcelUtils;
import com.han.test.springboot_test3.utils.excel.Student;
import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class ExcelTest {
    /**
     * 测试Excel导出
     */
    @Test
    public void testExcel4jToExport() throws Exception{
        List<List<String>> list = new ArrayList<>();
        List<String> header = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<String> oneColumn = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                oneColumn.add(i + "--" + j);
            }
            list.add(oneColumn);
            header.add(String.valueOf(i));
        }
        ExcelUtils.getInstance().exportObjects2Excel(list, header, "C:/Users/han/Desktop/student.xlsx");
    }
    
    @Test
    public void testExcel4jExportUseFunction() throws Exception {
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Student(10000L + i, "学生" + i, new Date(), 201));
        }
        ExcelUtils.getInstance().
                exportObjects2Excel(list, Student.class, true, "sheet0", true, "C:/Users/han/Desktop/student1.xlsx");
    }

    /**
     * 使用模板导出excel
     */
    @Test
    public void testExcelUseTemplate() throws Exception {
        String tempPath = "/templates/student.xlsx";
        List<Student> list = new ArrayList<>();
        list.add(new Student(10000L, "张三", new Date(), 201));
        list.add(new Student(10000L, "张三", new Date(), 201));
        list.add(new Student(10000L, "张三", new Date(), 201));
        list.add(new Student(10000L, "张三", new Date(), 201));
        list.add(new Student(10000L, "张三", new Date(), 201));
        list.add(new Student(10000L, "张三", new Date(), 201));
        list.add(new Student(10000L, "张三", new Date(), 201));
        list.add(new Student(10000L, "张三", new Date(), 201));
        list.add(new Student(10000L, "张三", new Date(), 201));
        list.add(new Student(10000L, "张三", new Date(), 201));
        list.add(new Student(10000L, "张三", new Date(), 201));
        Map<String, String> data = new HashMap<>();
        data.put("title","美丽的白衬衫");
        data.put("info", "我怎么可以这么帅");

        //基于模板导出excel表格
        ExcelUtils.getInstance().exportObjects2Excel(tempPath, 0, list, data, Student.class, false, "C:/Users/han/Desktop/student2.xlsx");
    }

    @Test
    public void testStream(){
        List<Student> list = new ArrayList<>();
        list.add(new Student(10000L, "张一", new Date(), 18));
        list.add(new Student(10001L, "张二", new Date(), 38));
        list.add(new Student(10002L, "张三", new Date(), 58));
        list.add(new Student(10003L, "张四", new Date(), 78));
        Map<String, List<Student>> collect = list.stream()
                .filter(student -> student.getId() >= 10000L)
                .collect(Collectors.groupingBy((student)-> {
                    if(student.getClasses() > 60){
                        return "老年人";
                    } else if (student.getClasses() > 40) {
                        return "中年人";
                    } else if (student.getClasses() > 20) {
                        return "青年人";
                    } else {
                        return "少年";
                    }
                }));
//                .collect(Collectors.reducing(0, Student::getId, (i, j) -> i + j));//有问题
//        collect.forEach(System.out::println);
        System.out.println(collect);
    }
    
    @Test
    public void testClass() throws Exception{
        BeanInfo beanInfo = Introspector.getBeanInfo(Student.class, Object.class);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd: pds) {
            if ("name".equals(pd.getName())) {
                Method readMethod = pd.getReadMethod();
                Object invoke = readMethod.invoke(new Student());
                System.out.println(invoke);

            }
        }
    }

//    public String exportAsNeed(List<SeaShip> seaShipList, String need) throws Exception {
//        StringBuffer collectEle = new StringBuffer();
//        for (SeaShip seaShip: seaShipList) {
//            //获取need对应的方法
//            Method method = seaShip.getClass().getMethod(need);
//            //执行方法
//            String ele = (String) method.invoke(seaShip);
//            if (ele != null && !ele.isEmpty()) {
//                collectEle.append(ele).append("/n");
//            }
//        }
//        return collectEle.toString();
//    }
}
