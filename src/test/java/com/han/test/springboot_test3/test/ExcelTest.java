package com.han.test.springboot_test3.test;


import com.github.crab2died.ExcelUtils;
import com.han.test.springboot_test3.domain.Backlog;
import com.han.test.springboot_test3.domain.FamilyBusinessControl;
import com.han.test.springboot_test3.mapper.BacklogMapper;
import com.han.test.springboot_test3.utils.excel.Student;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.hssf.record.PageBreakRecord;
import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ExcelTest {
    /**
     * 测试Excel导出
     */
    @Test
    public void testExcel4jToExport() throws Exception {
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
        data.put("title", "美丽的白衬衫");
        data.put("info", "我怎么可以这么帅");

        //基于模板导出excel表格
        ExcelUtils.getInstance().exportObjects2Excel(tempPath, 0, list, data, Student.class, false, "C:/Users/han/Desktop/student2.xlsx");
    }

    @Test
    public void testStream() {
        List<Student> list = new ArrayList<>();
        list.add(new Student(10000L, "张一", new Date(), 18));
        list.add(new Student(10001L, "张二", new Date(), 38));
        list.add(new Student(10002L, "张三", new Date(), 58));
        list.add(new Student(10003L, "张四", new Date(), 78));
        Map<String, List<Student>> collect = list.stream()
                .filter(student -> student.getId() >= 10000L)
                .collect(Collectors.groupingBy((student) -> {
                    if (student.getClasses() > 60) {
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
    public void testClass() throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(Student.class, Object.class);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
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

    /**
     * foreach和java8新特性比较测试
     */
    @Test
    public void testCompareTime() {
        List<Integer> test1 = new ArrayList<>();
        List<Integer> test2 = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            test1.add(i);
            test2.add(i);
        }
        Date begin1 = new Date();
        for (int i = 0; i < 1000; i++) {
            System.out.println(test1.indexOf(i));
        }
        Date end1 = new Date();

        Date begin2 = new Date();
        test2.stream().forEach(System.out::println);
        Date end2 = new Date();

        System.out.println(end1.getTime() - begin1.getTime());
        System.out.println(end2.getTime() - begin2.getTime());

    }

    /**
     * 虚拟机相关测试
     */
    @Test
    public void testJVM() {
        List<GarbageCollectorMXBean> garbageCollectorMXBeansList = ManagementFactory.getGarbageCollectorMXBeans();
        garbageCollectorMXBeansList.forEach(garbageCollectorMXBeans -> System.out.println(garbageCollectorMXBeans.getName()));
    }

    /**
     * 测试内存不足异常,堆溢出
     * VM Args：-Xms20m-Xmx20m-XX：+HeapDumpOnOutOfMemoryError
     */
    @Test
    public void testOutOfMemory() {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true) {
            list.add(new OOMObject());
        }
    }

    static class OOMObject {
    }

    /**
     * 测试递归深度,和栈溢出
     * VM Args：-Xss128k   --> 934
     * 默认 --> 28192
     *
     * @author zzm
     */
    class JavaVMStackSOF {
        private int stackLength = 1;

        public void stackLeak() {
            stackLength++;
            stackLeak();
        }
    }

    @Test
    public void testJVM2() {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length：" + oom.stackLength);
            throw e;
        }
    }

    /**
     * 测试方法区和运行时常量池溢出
     * VM Args：-XX：PermSize=10M-XX：MaxPermSize=10M
     *
     * @author zzm
     * java7以后,这个方法将一直执行下去
     */
    @Test
    public void testJNM2() {
        //使用List保持着常量池引用，避免Full GC回收常量池行为
        List<String> list = new ArrayList<String>();
        //10MB的PermSize在integer范围内足够产生OOM了
        int i = 0;
        while (true) {
            //如果字符串常量池中已经包含一个等
            //于此String对象的字符串，则返回代表池中这个字符串的String对象;否则，将此String对象包
            //含的字符串添加到常量池中，并且返回此String对象的引用。
            list.add(String.valueOf(i++).intern());
        }
    }

    /**
     * String.intern()返回引用测试
     *
     * 这段代码在JDK 1.6中运行，会得到两个false，而在JDK 1.7中运行，会得到一个true和一
     * 个false。产生差异的原因是：在JDK 1.6中，intern()方法会把首次遇到的字符串实例复制
     * 到永久代中，返回的也是永久代中这个字符串实例的引用，而由StringBuilder创建的字符串
     * 实例在Java堆上，所以必然不是同一个引用，将返回false。而JDK 1.7(以及部分其他虚拟
     * 机，例如JRockit)的intern()实现不会再复制实例，只是在常量池中记录首次出现的实例
     * 引用，因此intern()返回的引用和由StringBuilder创建的那个字符串实例是同一个。对str2比
     * 较返回false是因为“java”这个字符串在执行StringBuilder.toString()之前已经出现过，字符串
     * 常量池中已经有它的引用了，不符合“首次出现”的原则，而“计算机软件”这个字符串则是首
     * 次出现的，因此返回true。
     */
    @Test
    public void testIntern() {
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);
    }

    /**
     测试引用计数算法的缺陷
     *testGC()方法执行后，objA和objB会不会被GC呢？
     *
     *@author zzm
     */
    class ReferenceCountingGC{
        public Object instance=null;
        private static final int _1MB=1024*1024;
        /**
         *这个成员属性的唯一意义就是占点内存，以便能在GC日志中看清楚是否被回收过
         */
        private byte[] bigSize=new byte[2*_1MB];
    }
    
    @Test
    public void testJVM3(){
        ReferenceCountingGC objA=new ReferenceCountingGC();
        ReferenceCountingGC objB=new ReferenceCountingGC();
        objA.instance=objB;
        objB.instance=objA;
        objA=null;
        objB=null;
        //假设在这行发生GC,objA和objB是否能被回收？
        System.gc();
    }

    /**
     * 测试String.format()
     */
    @Test
    public void testFormat(){
        String a = new String();
        add(a);
        System.out.println(a + " " +a.hashCode());
    }

    public void add(String a) {
        a = "123";
        System.out.println(a + " " +a.hashCode());
    }

    /**
     * 测试同步问题
     */
    @Test
    public void testSync(){

    }

    /**
     * 测试时间格式异常
     */
    @Test
    public void testDate() {
        String value = "2018-01-01";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = simpleDateFormat.parse(String.valueOf(value));
            System.out.println(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 自动执行
     */
    @Test
    public void testAutoRun(){
        String str = "2.0";
        if (str.contains(".") && !str.contains("E")) {
            str = String.valueOf(Double.valueOf(str).longValue());
        } else if (str.contains("E")) {
            str = String.valueOf(Double.valueOf(str).longValue());
        }
        System.out.println(str);
    }

    /**
     * 内省机制
     */
    @Test
    public void testBean() throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Student student = new Student();
        BeanInfo userBeanInfo = Introspector.getBeanInfo(Student.class, Object.class) ;
        PropertyDescriptor[] pds = userBeanInfo.getPropertyDescriptors() ;
        for (PropertyDescriptor pd : pds) {
            if ("java.lang.String".equals(pd.getPropertyType().getName())) {
                Method method = pd.getWriteMethod();
                method.invoke(student, "");
            }

        }
        System.out.println(student);
    }
    
    @Test
    public void testTime() {
        System.out.println(new Date(1557417600000L).toLocaleString());
    }
    
    @Test
    public void testIterator(){
        List<Student> list = new ArrayList<>();
        list.add(new Student("1"));
        list.add(new Student("2"));
        list.add(new Student("3"));
        list.add(new Student("4"));
        Iterator<Student> iterator = list.iterator();
        while (iterator.hasNext()) {
            Student next = iterator.next();
            next.setName("5");
        }

        for (Student student : list) {
            System.out.println(student.getName());
        }
    }

    @Test
    public void testString() throws InterruptedException {
        Date date1 = new Date();
        Thread.sleep(10000);
        Date date2 = new Date();
        System.out.println(sameDate(date1, date2));
    }

    private static boolean sameDate(Date d1, Date d2) {
        LocalDate localDate1 = ZonedDateTime.ofInstant(d1.toInstant(), ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = ZonedDateTime.ofInstant(d2.toInstant(), ZoneId.systemDefault()).toLocalDate();
        return localDate1.isEqual(localDate2);
    }

    @Test
    public void testComparator(){
        List<ComparatorTest> list = new ArrayList<>();
        ComparatorTest comparatorTest1 = new ComparatorTest(4, BigDecimal.ONE);
        ComparatorTest comparatorTest2 = new ComparatorTest(3, BigDecimal.ONE);
        ComparatorTest comparatorTest3 = new ComparatorTest(4, BigDecimal.ONE);
        ComparatorTest comparatorTest4 = new ComparatorTest(4, BigDecimal.ZERO);
        list.add(comparatorTest1);
        list.add(comparatorTest2);
        list.add(comparatorTest3);
        list.add(comparatorTest4);

        ComparatorTest comparatorTest = list.stream()
                .max(Comparator.comparingInt(ComparatorTest::getWeight).thenComparing(ComparatorTest::getCoox, Comparator.nullsLast(BigDecimal::compareTo).reversed()))
                .get();
        System.out.println(comparatorTest.getWeight() + " " + comparatorTest.getCoox());
    }

    @Getter
    @Setter
    class ComparatorTest {
        public ComparatorTest(Integer weight, BigDecimal coox) {
            this.weight = weight;
            this.coox = coox;
        }

        private Integer weight;
        private BigDecimal coox;
    }

    @Test
    public void testInteger(){
        int weightNum = new BigDecimal(700).divide(new BigDecimal(8), 0, BigDecimal.ROUND_HALF_UP).intValue();
        System.out.println(weightNum);
    }
    
    @Test
    public void testBigDecimal(){
        BigDecimal matWeight = new BigDecimal(25);
        BigDecimal measureWeight = new BigDecimal(326);
        BigDecimal totalSum = new BigDecimal(12);
        //物料单位重量
        BigDecimal errorRangeDown = new BigDecimal(-1);
        BigDecimal errorRangeUp = new BigDecimal(1);
        measureWeight = measureWeight.subtract(new BigDecimal(90));
        //根据承重计算每托货物的平均重量
        BigDecimal oneWeightNum = measureWeight.divide(matWeight, 3, RoundingMode.HALF_UP);
        //计算与实际货物的偏差
        BigDecimal diffValue = oneWeightNum.subtract(matWeight);
        boolean isOk =  (diffValue.compareTo(errorRangeDown) >= 0&& diffValue.compareTo(errorRangeUp) <= 0);
        if ( isOk ) {
            //如果在误差范围内,则实收 = 应收
            System.out.println("ok" + totalSum);
        } else {
            int weightNum = measureWeight.divide(matWeight, 0, BigDecimal.ROUND_HALF_UP).intValue();
            System.out.println("noOK" + weightNum);
        }
    }

    @Test
    public void testList(){
        List<String> list = new ArrayList<>(Collections.singletonList("111"));
        list.add("999");

    }

    /**
     * Excel分析读取
     */
    @Test
    public void excelForStock() {
        //亲属关系备注索引
        int detailIndex = 5;
        long startTime = System.currentTimeMillis();
        String path = "D:\\wisdom\\myProject\\springboot_test3-master\\src\\main\\resources\\excel\\family_business_control.xlsx";
        try {
            //最终给算法的数据结构
            Map<String, Map<String, List<List<String>>>> finalGroup = new HashMap<>();

            // 不基于注解,将Excel内容读至List<List<String>>对象内
            List<List<String>> lists = ExcelUtils.getInstance().readExcel2List(path, 2, 16412, 0);
            System.out.println("读取Excel至String数组：");
            //首先根据股票号分组
            Map<String, List<List<String>>> bigGroup = lists.stream().collect(Collectors.groupingBy(data -> data.get(0)));

            //根据日期分组
            bigGroup.entrySet().forEach(data -> {
                List<List<String>> list = data.getValue();
                Map<String, List<List<String>>> smallGroup = list.stream().collect(Collectors.groupingBy(d -> d.get(2)));
                finalGroup.put(data.getKey(), smallGroup);
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


            //查看结果
            finalGroup.forEach((key, value) -> {
                value.forEach((childKey, childValue) -> {
                    childValue.forEach(data -> {
                        System.out.println(key + "->" + childKey + "->" + data);
                    });
                });
            });

            List<FamilyBusinessControl> list = resultData.stream()
                    .sorted(Comparator.comparing(FamilyBusinessControl::getStockNum).thenComparing(FamilyBusinessControl::getDateStr))
                    .collect(Collectors.toList());

            list.forEach(System.out::println);

            //4.保存数据库
//            familyBusinessControlMapper.insertList(list);


        } catch (Exception e) {
            e.printStackTrace();
        }
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
