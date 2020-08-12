package com.han.test.springboot_test3.test;

import com.han.test.springboot_test3.mapper.WmsAlleyMapper;
import com.han.test.springboot_test3.service.arithmetic.AlleyTypeAndNum;
import com.han.test.springboot_test3.service.arithmetic.ComputeParam;
import com.han.test.springboot_test3.service.arithmetic.StorageAllotArithmetic;
import com.han.test.springboot_test3.service.arithmetic.WmsAlleyDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestWork {
    @Autowired
    WmsAlleyMapper wmsAlleyMapper;


    @Test
    public void testProjectWeightCompute(){
        //货物类型
        String goodsType = "20";
        //货物热度
        Integer goodsHot = 0;
        //需要的总仓位
        int sum = 13;
        //查出所有的空巷道
        List<WmsAlleyDTO> datas = wmsAlleyMapper.selectAll();
        //查出所有的非满巷道
        List<WmsAlleyDTO> notFull = wmsAlleyMapper.selectNotFull();
        notFull.forEach(a -> {
            a.setGoodsNum(2);
        });
        //查出所有当前货物的巷道
        List<WmsAlleyDTO> goodsData = wmsAlleyMapper.selectByGoodsType(goodsType);
        //查找巷道类型,和数量
        List<AlleyTypeAndNum> alleyTypeAndNums = wmsAlleyMapper.selectAllAlleyType();



        ComputeParam computeParam = new ComputeParam();
        computeParam.setAlleyTypeAndNums(alleyTypeAndNums);
        computeParam.setEmptyAlleys(datas);
        computeParam.setGoodsAlley(goodsData);
        computeParam.setNotFullAlleys(notFull);
        computeParam.setSum(sum);
        computeParam.setGoodsHot(goodsHot);


        StorageAllotArithmetic storageAllotArithmetic = new StorageAllotArithmetic();
        List<WmsAlleyDTO> wmsAlleyDTOS = storageAllotArithmetic.computeAlley(computeParam);

        for (WmsAlleyDTO wmsAlleyDTO : wmsAlleyDTOS) {
            System.out.println("巷道 " + wmsAlleyDTO.getAlleyCode() + " 的深度为:" + wmsAlleyDTO.getStorageLocCount());
        }
        
        System.out.println("end");

    }










    @Test
    public void testSort(){
        HashMap<Integer, Integer> alleyTypeAndNumMap = new HashMap<>();
        alleyTypeAndNumMap.put(5, 12);
        alleyTypeAndNumMap.put(4, 15);
        alleyTypeAndNumMap.put(3, 8);
        alleyTypeAndNumMap.put(1, 23);
        List<AlleyTypeAndNum> alleyTypeAndNums = new ArrayList<>();
        Set<Integer> keys = alleyTypeAndNumMap.keySet();
        while (true) {
            AlleyTypeAndNum alleyTypeAndNum = new AlleyTypeAndNum();
            alleyTypeAndNum.setStorageLocCount(0);
            Integer removeKey = null;
            for (Integer key : keys) {
                if (key > alleyTypeAndNum.getStorageLocCount()) {
                    alleyTypeAndNum.setStorageLocCount(key);
                    alleyTypeAndNum.setNum(alleyTypeAndNumMap.get(key));
                    alleyTypeAndNums.add(alleyTypeAndNum);
                    removeKey = key;
                    break;
                }
            }
            keys.remove(removeKey);
            if (keys.size() == 0) {
                break;
            }
        }
        System.out.println( alleyTypeAndNums);
    }

//    @Test
//    public void testTwo(){
//        for (int i = 1; i <= 30; i++) {
//            System.out.println("当总数为" + i + "的时候----------------------");
//            testOne(i);
//        }
//    }
//    @Test
//    public void testThree(){
//        testOne(29);
//    }


    /**
     *
     * @param sum 总储位数
     */
    //    @Test
    public int[] testOne(int sum, int[] h){
//        int sum = 12;
//        int[] h = new int[]{5, 4, 3, 1};
//        int[] h = new int[]{4, 3, 1};
//        int[] h = new int[]{5, 3, 1};
//        int[] h = new int[]{5, 4, 1};
//        int[] h = new int[]{5, 4, 3};
//        int[] h = new int[]{1};
        //每种巷道的数量
        int[] a = null;

        //总巷道数
        int nums = sum / h[0];
        int excess = sum % h[0];
        if (excess != 0) {
            nums += 1;
        }

        //设置最大深度巷道数量为 总巷道数 - 1
        for (int i = 0; i < h.length; i++) {
            int[] tmpA = new int[]{0, 0, 0, 0};
            tmpA[0] = nums - 1;
            tmpA[i] += 1;
            int tmpSum = 0;
            for (int j = 0; j < h.length; j++) {
                tmpSum += tmpA[j] * h[j];
            }
            if (tmpSum == sum) {
                a = tmpA;
                break;
            }
        }

        //设置最大深度巷道数量为 总巷道数 - 2
        if (a == null && nums > 1) {
            for (int i = 2; i < h.length; i++) {
                int[] tmpA = new int[]{0, 0, 0, 0};
                tmpA[0] = nums - 2;
                tmpA[1] = 1;
                tmpA[i] = 1;
                int tmpSum = 0;
                for (int j = 0; j < h.length; j++) {
                    tmpSum += tmpA[j] * h[j];
                }
                if (tmpSum == sum) {
                    a = tmpA;
                    break;
                }
            }
        }

        //当没有正好放下的情况时
        if (a == null) {
            int m = sum / h[0];
            int n = sum % h[0];
            a = new int[]{m, 0, 0, 0};
            //TODO 检查m是否大于现有的巷道数

            for (int i = 0; i < h.length; i++) {
                if (n <= h[h.length - i - 1]) {
                    a[h.length - i - 1] += 1;
                    break;
                }
            }

        }

        for (int i = 0; i < a.length; i++) {
            if (a[i] != 0) {
                System.out.println("深度为" + h[i] + "的巷道需要的数量为:" + a[i]);
            }
        }

        return a;

    }

    //==============================================================================================
    
    @Test
    public void testWeight(){
        long startTime = System.currentTimeMillis();
        //====================上层需要给到的参数=============
        //货物类型
        String goodsType = "20";
        //货物热度
        Integer goodsHot = 0;

        //需要的总仓位
        int sum = 13;


        //冷热区权重基数
        int HotBaseNum = 100;
        //楼层权重基数
        int floorBaseNum = 50;
        //街区权重基数
        int areaBaseNum = 1000;
        //建议巷道权重基数
        int adviseBaseNum = 5000;

        //====================================================

        //查出所有的空巷道
        List<WmsAlleyDTO> datas = wmsAlleyMapper.selectAll();
        //查出所有当前货物的巷道
        List<WmsAlleyDTO> goodsData = wmsAlleyMapper.selectByGoodsType(goodsType);
        //查找巷道类型,和数量
        List<AlleyTypeAndNum> alleyTypeAndNums = wmsAlleyMapper.selectAllAlleyType();


        //未分配的仓位数
        int remain = sum;


        int[] h = new int[alleyTypeAndNums.size()];
        for (int i = 0; i < alleyTypeAndNums.size(); i++) {
            h[i] = alleyTypeAndNums.get(i).getStorageLocCount();
        }

        //根据需要的总货位和巷道类型和数量给出最优解
        int[] result = testOne(sum, h);

        //TODO 优化result和h


        //初始化权重
        datas.forEach(data -> {
            Integer weightNum = data.getWeightNum();
            //根据冷热区减少权值
            int hotWeight = (data.getHotIndex() - goodsHot) * HotBaseNum;
            weightNum -= ((hotWeight < 0) ? -hotWeight : hotWeight);
            //根据楼层加权值
            weightNum -= data.getLayerCode() * floorBaseNum;
            //根据推荐巷道增加权值
            for (int i = 0; i < h.length; i++) {
                if (h[i] == data.getStorageLocCount()) {
                    weightNum += adviseBaseNum * result[i];
                    break;
                }
            }
            data.setWeightNum(weightNum);
        });

        //查询合并目前的分布情况
        Map<String, Integer> areaMap = new HashMap<>();
        for (WmsAlleyDTO wmsAlley : goodsData) {
            String areaCode = wmsAlley.getAreaCode();
            areaMap.merge(areaCode, 1, Integer::sum);
        }
        datas.forEach(data -> {
            Integer areaNum = areaMap.get(data.getAreaCode());
            if (areaNum != null) {
                data.setWeightNum(data.getWeightNum() - areaNum * areaBaseNum);
            }

        });

        List<WmsAlleyDTO> needAlley = new ArrayList<>();
        while (remain > 0) {
            //获取权重最大的一个巷道
            Optional<WmsAlleyDTO> max = datas.stream().max(Comparator.comparingInt(WmsAlleyDTO::getWeightNum));
            WmsAlleyDTO wmsAlley = max.get();
            needAlley.add(wmsAlley);

            //把这个巷道从空巷道列表中移除,并添加到
            datas.remove(wmsAlley);
//            areaMap.merge(wmsAlley.getAreaCode(), 1, Integer::sum);

            datas.forEach(data -> {
                //根据区域减权重
                if (wmsAlley.getAreaCode().equals(data.getAreaCode())) {
                    data.setWeightNum(data.getWeightNum() - areaBaseNum);
                }
                //根据巷道减权重
                if (wmsAlley.getStorageLocCount().equals(data.getStorageLocCount())) {
                    data.setWeightNum(data.getWeightNum() - adviseBaseNum);
                }
            });

            //TODO 最后一个巷道需要加一些处理防止库位浪费问题
            remain -= wmsAlley.getStorageLocCount();
        }

        //TODO 更新巷道表,暂无锁定

        for (WmsAlleyDTO wmsAlley : needAlley) {
            wmsAlley.setGoodsType(goodsType);
            System.out.println("巷道 " + wmsAlley.getAlleyCode() + " 的深度为:" + wmsAlley.getStorageLocCount());
            wmsAlleyMapper.updateByCode(wmsAlley);
        }

        System.out.println("用时:" + (System.currentTimeMillis() - startTime));

    }

    @Autowired
    StorageAllotArithmetic storageAllotArithmetic;

    @Test
    public void testAri(){
        List<AlleyTypeAndNum> alleyTypeAndNums = new ArrayList<>();
        AlleyTypeAndNum alleyTypeAndNum3 = new AlleyTypeAndNum();
        alleyTypeAndNum3.setStorageLocCount(5);
        alleyTypeAndNum3.setNum(5);
        alleyTypeAndNums.add(alleyTypeAndNum3);

        AlleyTypeAndNum alleyTypeAndNum = new AlleyTypeAndNum();
        alleyTypeAndNum.setStorageLocCount(4);
        alleyTypeAndNum.setNum(20);
        alleyTypeAndNums.add(alleyTypeAndNum);

        AlleyTypeAndNum alleyTypeAndNum1 = new AlleyTypeAndNum();
        alleyTypeAndNum1.setStorageLocCount(3);
        alleyTypeAndNum1.setNum(20);
        alleyTypeAndNums.add(alleyTypeAndNum1);

        AlleyTypeAndNum alleyTypeAndNum2 = new AlleyTypeAndNum();
        alleyTypeAndNum2.setStorageLocCount(1);
        alleyTypeAndNum2.setNum(20);
        alleyTypeAndNums.add(alleyTypeAndNum2);

        int[] alleyTypeResult = storageAllotArithmetic.getAlleyTypeResult(35, alleyTypeAndNums);

        System.out.println(Arrays.toString(alleyTypeResult));
    }

}














