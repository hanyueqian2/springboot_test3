package com.han.test.springboot_test3.test;

import com.alibaba.fastjson.JSONArray;
import com.han.test.springboot_test3.domain.Backlog;
import com.han.test.springboot_test3.domain.PointInfo;
import com.han.test.springboot_test3.mapper.BacklogMapper;
import com.han.test.springboot_test3.service.arithmetic.AlleyTypeAndNum;
import com.han.test.springboot_test3.utils.constant.TestEnum;
import com.han.test.springboot_test3.utils.excel.MessageUtils;
import com.han.test.springboot_test3.service.RedisUtils;
import com.han.test.springboot_test3.utils.excel.Student;
import com.han.test.springboot_test3.utils.excel.Teacher;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import net.bytebuddy.agent.ByteBuddyAgent;
import org.checkerframework.checker.units.qual.min;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.print.BackgroundLookupListener;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class EasyTest {
    @Autowired
    MessageUtils messageUtils;
    @Autowired
    RedisUtils redisUtils;


    @Test
    public void testListClass(){
        Student student1 = new Student("001");
        Student student2 = new Student("002");
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);

        Teacher teacher = new Teacher();
        teacher.setStudents(students);
        listClass(teacher);

    }

    public void listClass(Teacher teacher) {
        Class<? extends Teacher> aClass = teacher.getClass();
        try {
            Method setStudents = aClass.getDeclaredMethod("getStudents");
            Object invoke = setStudents.invoke(teacher);
            List<Student> students = (List<Student>)invoke;
            System.out.println(students.get(0).getName());

            Field field = aClass.getDeclaredField("students");
//            field.get()
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
    

    @Test
    public void testEnglish(){
        String name = messageUtils.get("user.title");
        System.out.println(name);
    }

    @Test
    public void testRedis(){
        redisUtils.set("name", "han");
    }

    @Test
    public void testSplit(){
        
        int[] a = {1, 2, 3};
        
        System.out.println(Arrays.toString(a));
    }

    /**
     * 将巷道类型按照深度排序
     * @param alleyTypeAndNumMap
     * @return
     */
    private List<AlleyTypeAndNum> sortAlleyType(Map<Integer, Integer> alleyTypeAndNumMap) {
        Map<Integer, Integer> tmpAlleyTypeAndNumMap = new HashMap<>(alleyTypeAndNumMap);
        List<AlleyTypeAndNum> alleyTypeAndNums = new ArrayList<>();
        Set<Integer> keys = tmpAlleyTypeAndNumMap.keySet();
        while (keys.size() > 0) {
            AlleyTypeAndNum alleyTypeAndNum = new AlleyTypeAndNum();
            alleyTypeAndNum.setStorageLocCount(0);
            Integer removeKey = null;
            for (Integer key : keys) {
                if (key > alleyTypeAndNum.getStorageLocCount()) {
                    alleyTypeAndNum.setStorageLocCount(key);
                    alleyTypeAndNum.setNum(tmpAlleyTypeAndNumMap.get(key));
                    removeKey = key;
                }
            }
            alleyTypeAndNums.add(alleyTypeAndNum);
            keys.remove(removeKey);
        }
        return alleyTypeAndNums;
    }

    @Test
    public void testSortAlleyType(){
        Map<Integer, Integer> alleyTypeAndNumMap = new HashMap<>();
        alleyTypeAndNumMap.put(4, 5);
        alleyTypeAndNumMap.put(1, 5);
        alleyTypeAndNumMap.put(3, 5);
        alleyTypeAndNumMap.put(5, 5);
        List<AlleyTypeAndNum> alleyTypeAndNums = sortAlleyType(alleyTypeAndNumMap);
        for (AlleyTypeAndNum alleyTypeAndNum : alleyTypeAndNums) {
            System.out.println(alleyTypeAndNum.getStorageLocCount());

        }
    }


    @Test
    public void testStringInt(){
        Map<String, Integer> map = new HashMap<>();
        map.put("1", 1);
        map.put("2", 1);
        map.put("3", 1);
        map.put("4", 1);
        map.put("5", 1);

        map.put("1", map.get("1") + 1);
        
        System.out.println(map.get("1"));
    }

    @Test
    public void testTime(){
        int sum = 3;
        Map<Integer, Integer> optionsMap = new LinkedHashMap<>();
        optionsMap.put(1, 1);
        optionsMap.put(2, 1);
        optionsMap.put(3, 13);

        Map<Integer, Integer> integerIntegerMap = combinationAlgorithm(sum, optionsMap);
        for (Integer integer : integerIntegerMap.keySet()) {
            System.out.println(integer + ": " + integerIntegerMap.get(integer)) ;
        }


    }

    private Map<Integer, Integer> combinationAlgorithm(int sum, Map<Integer, Integer> optionsMap) {
        //将optionsMap按key从小到大排序
        Map<Integer, Integer> sequenceOptionsMap = new LinkedHashMap<>(5);
        Set<Integer> keys = new HashSet<>(optionsMap.keySet());
        //用于盛放所有的key
        int[] baseKeys = new int[keys.size()];
        int index = 0;
        while (keys.size() > 0) {
            Integer removeKey = null;
            for (Integer key : keys) {
                if (removeKey == null || removeKey > key) {
                    removeKey = key;
                }
            }
            if (removeKey > sum) {
                break;
            }
            baseKeys[index++] = removeKey;
            sequenceOptionsMap.put(removeKey, optionsMap.get(removeKey));
            keys.remove(removeKey);
        }

        if (sequenceOptionsMap.size() <= 0) {
            return null;
        }

        //计算
        int[] resultInt = new int[sequenceOptionsMap.size()];
        Arrays.fill(resultInt, 0);
        //指针,指向目前计算的位数,从0开始
        int point = 0;
        resultInt[point]++;
        /**
         * 核心算法
         */
        while (true) {
            //求和
            int currentSum = summation(resultInt, baseKeys);
            if (currentSum < sum) {
                //如果结果小于sum
                while (true) {
                    if (point >= sequenceOptionsMap.size()) {
                        return null;
                    } else if (resultInt[point] >= optionsMap.get(baseKeys[point])) {
                        resultInt[point] = 0;
                        point++;
                    } else {
                        resultInt[point]++;
                        point = 0;
                        break;
                    }
                }
            } else if (currentSum == sum) {
                //如果结果和sum相等则结束
                break;
            } else {
                //如果结果大于sum
                while (true) {
                    if (resultInt[resultInt.length - 1] > 0) {
                        return null;
                    }
                    for (int j = resultInt.length - 2; j >= 0; j++) {
                        if (resultInt[j] != 0) {
                            Arrays.fill(resultInt, 0);
                            resultInt[j+1]++;
                        }
                    }
                }
            }
        }

        //合成返回值
        Map<Integer, Integer> resultMap = new LinkedHashMap<>(5);
        for (int i = 0; i < resultInt.length; i++) {
            resultMap.put(baseKeys[i], resultInt[i]);
        }
        return resultMap;
    }

    private int summation(int[] param1, int[] param2) {
        int sum = 0;
        for (int i = 0; i < param1.length; i++) {
            sum += param1[i] * param2[i];
        }
        return sum;
    }


    @Test
    public void testObject() {
        Student student = new Student();
        student.setId(123L);

        System.out.println(student.getId());
        studentObject(student);
        System.out.println(student.getId());
    }

    public void studentObject(Student student) {
        student.setId(666L);

    }

    public void listObject(List<Student> strs) {
        List<Student> collect = strs.stream().filter(data -> "a".equals(data.getName())).collect(Collectors.toList());
        Student student = collect.get(0);
        strs.remove(student);
        System.out.println(strs.size());
    }

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

    @Test
    public void testSelectAllParam(){
        int[] allParam = new int[]{1,4,5,6,7,8,8,9};
        int[] ints = selectAllParam(4, 20, allParam);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(allParam[ints[i]]);
        }
    }

    public int[] selectAllParam(int needNum, int resultNum, int[] allParam) {
        int[] indexList = new int[needNum];

        //初始化
        for (int i = 0; i < needNum; i++) {
            indexList[i] = i;
        }

        while (true) {
            int length = allParam.length;
            int index = needNum - 1;
            //一次枚举
            while (true) {
                if (indexList[index] >= length - 1
                        || (index != needNum - 1 && indexList[index] >= indexList[index+1] - 1)) {
                    if (index > 1) {
                        index--;
                    } else {
                        break;
                    }
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
                break;
            }
        }

        int[] resultList = new int[indexList.length];
        for (int i = 0; i < indexList.length; i++) {
            resultList[i] = allParam[indexList[i]];
        }

        return resultList;
    }

    @Test
    public void testArrayList(){
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        String json = JSONArray.toJSONString(list);

        System.out.println(json);

    }
    
    @Test
    public void testJna(){
        CLibrary.INSTANCE.printf("Hello, World!");
    }

    /**
     * DLL动态库调用方法
     * @Description: 读取调用CDecl方式导出的DLL动态库方法
     * @author: LinWenLi
     * @date: 2018年7月18日 上午10:49:02
     */
    public interface CLibrary extends Library {
        // DLL文件默认路径为项目根目录，若DLL文件存放在项目外，请使用绝对路径。（此处：(Platform.isWindows()?"msvcrt":"c")指本地动态库msvcrt.dll）
        CLibrary INSTANCE = (CLibrary) Native.loadLibrary((Platform.isWindows() ? "msvcrt" : "c"), CLibrary.class);
//        CLibrary INSTANCE = (CLibrary) Native.loadLibrary("C:\\Users\\han_h\\Desktop\\msvcrt", CLibrary.class);

        // 声明将要调用的DLL中的方法,可以是多个方法(此处示例调用本地动态库msvcrt.dll中的printf()方法)
        void printf(String format, Object... args);
    }

    @Autowired
    BacklogMapper backlogMapper;
    @Test
    public void testSQL(){
        int page = 6 / 6;
        System.out.println(page);
    }
    
    @Test
    public void testList(){
        List<String> arrayList = new ArrayList<>();
        List<String> linkedList = new LinkedList<>();
        for (int i = 0; i < 100000; i++) {
            arrayList.add(String.valueOf(i));
            linkedList.add(String.valueOf(i));
        }
        //查询时间实验
        System.out.println("arrayList查询时间" + getSelectTime(arrayList));
        System.out.println("linedList查询时间" + getSelectTime(linkedList));
        //修改时间实验
        System.out.println("arrayList更新时间" + getUpdateTime(arrayList));
        System.out.println("linedList更新时间" + getUpdateTime(linkedList));
        //插入时间实验
        System.out.println("arrayList插入时间" + getInsertTime(arrayList));
        System.out.println("linedList插入时间" + getInsertTime(linkedList));
        //删除时间实验
        System.out.println("arrayList删除时间" + getDeleteTime(arrayList));
        System.out.println("linedList删除时间" + getDeleteTime(linkedList));
        System.out.println("------------------迭代器-------------------------");
        //迭代器插入实验
        System.out.println("arrayList迭代器插入时间" + getIteratorDeleteTime(arrayList));
        System.out.println("linedList迭代器插入时间" + getIteratorDeleteTime(linkedList));
        //迭代器删除实验

    }

    /**
     * 获取查询1000个元素的时间
     * @param list
     * @return
     */
    public long getSelectTime(List<String> list) {
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            String s = list.get(i * 100);
        }
        long time2 = System.currentTimeMillis();
        return time2 - time1;
    }

    /**
     * 获取更新1000个元素的时间
     * @param list
     * @return
     */
    public long getUpdateTime(List<String> list) {
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            int updateIndex = i * 100;
            list.set(updateIndex, String.valueOf(updateIndex));
        }
        long time2 = System.currentTimeMillis();
        return time2 - time1;
    }
    /**
     * 获取插入1000个元素的时间
     * @param list
     * @return
     */
    public long getInsertTime(List<String> list) {
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            int updateIndex = i * 100;
            list.add(updateIndex, String.valueOf(updateIndex));
        }
        long time2 = System.currentTimeMillis();
        return time2 - time1;
    }
    /**
     * 获取删除1000个元素的时间
     * @param list
     * @return
     */
    public long getDeleteTime(List<String> list) {
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            int updateIndex = i * 100;
            list.remove(updateIndex);
        }
        long time2 = System.currentTimeMillis();
        return time2 - time1;
    }
    /**
     * 获取迭代器删除所有元素的时间
     * @param list
     * @return
     */
    public long getIteratorDeleteTime(List<String> list) {
        long time1 = System.currentTimeMillis();
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        long time2 = System.currentTimeMillis();
        return time2 - time1;
    }
    /**
     * 获取迭代器插入1000个元素的时间
     * @param list
     * @return
     */
    public long getIteratorInsertTime(List<String> list) {
        long time1 = System.currentTimeMillis();
        ListIterator<String> stringListIterator = list.listIterator();
        for (int i = 0; i < 1000; i++) {
            stringListIterator.add(String.valueOf(i));
        }
        long time2 = System.currentTimeMillis();
        return time2 - time1;
    }

    @Test
    public void testMap(){
        HashMap<String, Integer> hashMap = new HashMap<>();
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
        Map<String, Integer> hashtable = new Hashtable<>();
        Map<String, Integer> treeMap = new TreeMap<>();

        hashMap.put("1", 1);
        hashMap.put("2", 2);
        hashMap.put("5", 5);
        hashMap.put("3", 3);
        hashMap.put("4", 4);

        hashMap.get("1");
    }

    @Test
    public void testThread(){
        String str = "OK";
        new Thread(() -> System.out.println(str)).start();
    }

    @Test
    public void testSet(){
        Set<String> hashSet = new HashSet<>();
        Set<String> linkedHashSet = new LinkedHashSet<>();
        Set<String> treeSet = new TreeSet<>();
    }

    @Test
    public void testStream(){
        int exec = TestEnum.ADD.exec(1, 2);
    }

    @Test
    public void testString(){
        String data = "DD12345678910123";
        if (data.length() > 15) {
            System.out.println(".." + data.substring(data.length() - 14));
        }
    }

    @Test
    public void testJump(){
        long startTime = System.currentTimeMillis();
        int count1 = solve1(40);
        long endTime = System.currentTimeMillis();
        System.out.println("青蛙的跳法1:" + count1 + " 耗时:" + (endTime - startTime) + "ms");
        startTime = System.currentTimeMillis();
        long count2 = solve2(10000);
        endTime = System.currentTimeMillis();
        System.out.println("青蛙的跳法2:" + count2 + " 耗时:" + (endTime - startTime) + "ms");
        startTime = System.currentTimeMillis();
        long count3 = solve3(10000);
        endTime = System.currentTimeMillis();
        System.out.println("青蛙的跳法3:" + count3 + " 耗时:" + (endTime - startTime) + "ms");
    }

    //记录中间结果
    Map<Integer, Long> centerResult = new HashMap<>();
    /**
     * 解法2:
     * @param i
     * @return
     */
    private long solve2(int i) {
        centerResult.put(1, 1L);
        centerResult.put(2, 2L);

        for (int j = 3; j <= i; j++) {
            Long first = centerResult.get(j - 1);
            Long second = centerResult.get(j - 2);
            centerResult.put(j, first + second);
        }

        return centerResult.get(i);
    }
    /**
     * 解法3:
     * @param i
     * @return
     */
    private long solve3(int i) {
        long f1 = 1;
        long f2 = 2;
        long sum = 0;

        for (int j = 3; j <= i; j++) {
            sum = f1 + f2;
            f1 = f2;
            f2 = sum;
        }

        return sum;
    }

    /**
     * 解法1: 递归
     * @param i
     * @return
     */
    private int solve1(int i) {
        if (i == 1 || i == 2) {
            return i;
        } else if (i <= 0) {
            return 0;
        } else {
            return solve1(i - 1) + solve1(i - 2);
        }
    }
    
    @Test
    public void testAlgorithm(){
        System.out.println(isMatch("asdfasddfd", ".*d.."));
    }

    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                }
                else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    /**
     * 比较s的第i个字符和p的第j个字符是否匹配
     * @return
     */
    public boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }
    
    @Test
    public void testStringMatch(){
        String[] words = {"word", "good", "best", "word"};
        List<Integer> list = stringMatch("wordgoodgoodgoodbestword", words);
        System.out.println(JSONArray.toJSONString(list));
    }

    /**
     *
     * @param str
     * @param words
     * @return
     */
    public List<Integer> stringMatch(String str, String[] words) {
        int size = words[0].length();
        List<Integer> result = new ArrayList<>();

        //循环遍历
        for (int i = 0; i <= str.length() - size; i++) {
            int j = i;

            String[] tmpWords = Arrays.copyOf(words, words.length);
            String tmpStr = str.substring(j, j + size);
            while (true) {
                int index = matchStr(tmpStr, tmpWords);
                if (index != -1) {
                    tmpWords[index] = "";
                    j += size;
                    if (j >= str.length()) {
                        break;
                    }
                    tmpStr = str.substring(j, j + size);
                    if (checkAllEmpty(tmpWords)) {
                        result.add(i);
                    }
                } else {
                    break;
                }
            }
        }

        return result;
    }

    /**
     * 检查单词是否都被匹配
     * @param words
     * @return
     */
    public boolean checkAllEmpty(String[] words) {
        for (String word : words) {
            if (!"".equals(word)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查字符串是否在单词中
     * @param str
     * @param words
     * @return
     */
    public int matchStr(String str, String[] words) {
        for (int i = 0; i < words.length; i++) {
            if (str.equals(words[i])) {
                return i;
            }
        }
        return -1;
    }

    @Test
    public void testBracket(){
        System.out.println(bracketNum(")()())"));
    }

    public int bracketNum(String str) {
        int maxNum = 0;
        int tmpMaxNum = 0;
        String bracket = "()";
        for (int i = 0; i < str.length(); ) {
            if (str.length() >= i + 2 && bracket.equals(str.substring(i, i+2))) {
                tmpMaxNum++;
                i = i+2;
                maxNum = Math.max(maxNum, tmpMaxNum);
            } else {
                tmpMaxNum = 0;
                i++;
            }
        }
        return maxNum * 2;
    }


}


