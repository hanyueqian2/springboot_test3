package com.han.test.springboot_test3.service.arithmetic;

import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 入库调度查找巷道算法
 * @author han
 */
@Service
public class StorageAllotArithmetic {

    /**
     * 冷热区权重基数
     */
    private final static int HOT_BASE_NUM = 100;
    /**
     * 楼层权重基数
     */
    private final static int FLOOR_BASE_NUM = 50;
    /**
     * 街区权重基数
     */
    private final static int AREA_BASE_NUM = 1000;
    /**
     * 建议巷道权重基数
     */
    private final static int ADVISE_BASE_NUM = 5000;

    /**
     * 库区权重基数
     */
    private final static int STORAGE_BASE_NUM = 10000;
    /**
     * 每个剩余库位权重基数
     */
    private final static int REMAIN_BASE_NUM = 2000;
    /**
     * 基础权值
     */
    private final static int BASE_NUM = 1000;

    /**
     * 按照最少巷道原则计算最佳的巷道类型选择
     * @param sum 需要的库位总数量
     * @param alleyTypeAndNumList 巷道类型及对应数量
     * @return 建议的巷道分布
     */
    private int[] getOptimalAllocation(int sum, List<AlleyTypeAndNum> alleyTypeAndNumList) {
        //提取巷道类型,用于后面的计算
        int[] alleysType = new int[alleyTypeAndNumList.size()];
        for (int i = 0; i < alleyTypeAndNumList.size(); i++) {
            alleysType[i] = alleyTypeAndNumList.get(i).getStorageLocCount();
        }

        //每种巷道的数量
        int[] alleysNum = null;

        //计算总巷道数
        int nums = sum / alleysType[0];
        int excess = sum % alleysType[0];
        if (excess != 0) {
            nums += 1;
        }

        //1.首先先假设 最大深度巷道数量 = 总巷道数 - 1
        for (int i = 0; i < alleysType.length; i++) {
            int[] tmpAlleysNum = new int[]{0, 0, 0, 0};
            tmpAlleysNum[0] = nums - 1;
            tmpAlleysNum[i] += 1;
            //求和,求假设的方案是否合适
            int tmpSum = 0;
            for (int j = 0; j < alleysType.length; j++) {
                tmpSum += tmpAlleysNum[j] * alleysType[j];
            }
            if (tmpSum == sum) {
                alleysNum = tmpAlleysNum;
                break;
            }
        }

        //2.之后假设 最大深度巷道数量 = 总巷道数 - 2
        if (alleysNum == null && nums > 1) {
            for (int i = 2; i < alleysType.length; i++) {
                int[] tmpAlleysNum = new int[]{0, 0, 0, 0};
                tmpAlleysNum[0] = nums - 2;
                tmpAlleysNum[1] = 1;
                tmpAlleysNum[i] = 1;
                int tmpSum = 0;
                for (int j = 0; j < alleysType.length; j++) {
                    tmpSum += tmpAlleysNum[j] * alleysType[j];
                }
                if (tmpSum == sum) {
                    alleysNum = tmpAlleysNum;
                    break;
                }
            }
        }

        //3. 当没有正好放下的情况时
        if (alleysNum == null) {
            //最深巷道的数量
            int maxAlleyNum = sum / alleysType[0];
            //剩余需要的库位
            int remainNum = sum % alleysType[0];
            alleysNum = new int[]{maxAlleyNum, 0, 0, 0};

            for (int i = 0; i < alleysType.length; i++) {
                if (remainNum <= alleysType[alleysType.length - i - 1]) {
                    alleysNum[alleysType.length - i - 1] += 1;
                    break;
                }
            }

        }

        return alleysNum;
    }

    /**
     * 根据需要的总货位和巷道类型和数量给出最优解
     * @param sum 需要的总货位数
     * @param alleyTypeAndNumList 巷道类型和数量
     * @return 巷道种类分布最优结果
     * @throws RuntimeException 当前货位总数小于需要的总货位数 则报错
     */
    public int[] getAlleyTypeResult(int sum, List<AlleyTypeAndNum> alleyTypeAndNumList) throws RuntimeException {
        int sumEmptyStore = 0;
        //剩余的巷道种类
        List<AlleyTypeAndNum> remainAlleyTypeList = new ArrayList<>(alleyTypeAndNumList);
        //巷道总种类数
        int alleyTypeNum = alleyTypeAndNumList.size();

        //用于保存结果的map,最后map的value将会以数组的形式返回
        Map<Integer, Integer> resultMap = new LinkedHashMap<>();
        //结果集
        for (AlleyTypeAndNum alleyTypeAndNum : remainAlleyTypeList) {
            resultMap.put(alleyTypeAndNum.getStorageLocCount(), 0);
            sumEmptyStore += alleyTypeAndNum.getNum() * alleyTypeAndNum.getStorageLocCount();
        }

        checkParam(sumEmptyStore < sum, "空间不足");

        int[] alleysTypeResult = null;

        while (true) {
            //计算最佳组合
            alleysTypeResult = getOptimalAllocation(sum, remainAlleyTypeList);
            int needRemoveIndex = -1;
            for (int i = 0; i < remainAlleyTypeList.size(); i++) {
                AlleyTypeAndNum alleyTypeAndNum = remainAlleyTypeList.get(i);
                //检查查询出的结果中,是否有某种巷道深度的数量大于现有(剩余)的巷道数量
                if (alleyTypeAndNum.getNum() < alleysTypeResult[i]) {
                    //有问题,开始处理
                    resultMap.put(alleyTypeAndNum.getStorageLocCount(), alleyTypeAndNum.getNum());
                    sum -= alleyTypeAndNum.getStorageLocCount() * alleyTypeAndNum.getNum();
                    //减去这类巷道
                    needRemoveIndex = i;
                    break;
                }
            }
            if (needRemoveIndex == -1) {
                break;
            }
            remainAlleyTypeList.remove(needRemoveIndex);

        }

        //整合处理结果
        for (int i = 0; i < remainAlleyTypeList.size(); i++) {
            resultMap.put(remainAlleyTypeList.get(i).getStorageLocCount(), alleysTypeResult[i]);
        }
        alleysTypeResult = new int[alleyTypeAndNumList.size()];
        for (int i = 0; i < alleyTypeNum; i++) {
            alleysTypeResult[i] = resultMap.get(alleyTypeAndNumList.get(i).getStorageLocCount());
        }

        return alleysTypeResult;
    }

    /**
     * 对外暴露的计算巷道的工具
     * @param computeParam
     * @return
     */
    public List<WmsAlleyDTO> computeAlley(ComputeParam computeParam) {
        //校验
        checkParam(computeParam == null,                        "参数异常");
        checkParam(computeParam.getAlleyTypeAndNums() == null,  "巷道类型不能为空");
        checkParam(computeParam.getGoodsAlley() == null,        "含有当前货物的巷道不能为空");
        checkParam(computeParam.getEmptyAlleys() == null,       "空巷道不能为空");
        checkParam(computeParam.getGoodsHot() == null,          "货物热度不能为空");
        checkParam(computeParam.getSum() == null,               "总仓位数不能为空");
        checkParam(computeParam.getSum() <= 0,                  "总仓位数不能小于0");

        List<WmsAlleyDTO> allNeedAlley = new ArrayList<>();

        //计算非满巷道结果
        if (computeParam.getNotFullAlleys() != null && computeParam.getNotFullAlleys().size() > 0) {
            List<WmsAlleyDTO> notFullAlleyResult = getNotFullAlleyResult(computeParam);
            allNeedAlley.addAll(notFullAlleyResult);
        }

        //计算空巷道结果
        if (computeParam.getSum() > 0) {
            List<WmsAlleyDTO> emptyAlleyResult = getEmptyAlleyResult(computeParam);
            allNeedAlley.addAll(emptyAlleyResult);
        }

        return allNeedAlley;
    }

    private void checkParam(boolean error, String errorMsg) {
        if (error) {
            throw new RuntimeException(errorMsg);
        }
    }

    /**
     * 计算非满巷道结果(补充巷道的结果)
     * @param computeParam 计算参数
     * @return 计算结果,计算出来的所有巷道
     */
    private List<WmsAlleyDTO> getNotFullAlleyResult(ComputeParam computeParam) {
        List<WmsAlleyDTO> notFullAlleys = computeParam.getNotFullAlleys();
        //初始化权重
        notFullAlleys.forEach(data -> {
            //根据位置加权值
            int weightNum = BASE_NUM - data.getCooY().intValue();

            //根据冷热区减少权值
            int hotWeight = (data.getHotIndex() - computeParam.getGoodsHot()) * HOT_BASE_NUM;
            weightNum -= ((hotWeight < 0) ? -hotWeight : hotWeight);

            data.setWeightNum(weightNum);
        });

        //最终结果
        List<WmsAlleyDTO> needAlley = new ArrayList<>();
        //未分配的仓位数
        int remain = computeParam.getSum();

        while (notFullAlleys.size() > 0 && remain > 0) {
            //获取权重最大的一个巷道
            WmsAlleyDTO wmsAlley = notFullAlleys.stream().max(Comparator.comparingInt(WmsAlleyDTO::getWeightNum)).get();
            //空库位数
            int emptyPlaceNum = wmsAlley.getStorageLocCount() - wmsAlley.getGoodsNum();
            //赋值需要新增货物的库位数
            if (remain >= emptyPlaceNum) {
                wmsAlley.setNewGoodsNeed(emptyPlaceNum);
            } else {
                wmsAlley.setNewGoodsNeed(remain);
            }
            needAlley.add(wmsAlley);
            notFullAlleys.remove(wmsAlley);

            notFullAlleys.forEach(data -> {
                //根据区域减权重
                if (wmsAlley.getAreaCode().equals(data.getAreaCode())) {
                    data.setWeightNum(data.getWeightNum() - AREA_BASE_NUM);
                }
                //根据楼层减权重
                if (wmsAlley.getLayerCode().equals(data.getLayerCode())) {
                    data.setWeightNum(data.getWeightNum() - FLOOR_BASE_NUM);
                }
            });
            remain -= emptyPlaceNum;
        }
        computeParam.setSum(Math.max(remain, 0));
        return needAlley;
    }


    /**
     * 计算空巷道结果
     * @param computeParam 计算参数
     * @return 计算结果,计算出来的所有巷道
     */
    private List<WmsAlleyDTO> getEmptyAlleyResult(ComputeParam computeParam) {
        List<AlleyTypeAndNum> alleyTypeAndNums = computeParam.getAlleyTypeAndNums();
        List<WmsAlleyDTO> emptyAlleys = computeParam.getEmptyAlleys();


        //根据需要的总货位和巷道类型和数量给出最优解
        int[] alleyTypeResult = getAlleyTypeResult(computeParam.getSum(), alleyTypeAndNums);

        //初始化权重
        emptyAlleys.forEach(data -> {
            //根据位置加权值
            int weightNum = BASE_NUM - data.getCooY().intValue();

            //根据冷热区减少权值
            int hotWeight = (data.getHotIndex() - computeParam.getGoodsHot()) * HOT_BASE_NUM;
            weightNum -= ((hotWeight < 0) ? -hotWeight : hotWeight);

            //根据推荐巷道增加权值
            for (int i = 0; i < alleyTypeResult.length; i++) {
                if (alleyTypeAndNums.get(i).getStorageLocCount().equals(data.getStorageLocCount())) {
                    weightNum += alleyTypeResult[i] * ADVISE_BASE_NUM;
                    break;
                }
            }
            data.setWeightNum(weightNum);
        });

        //查询合并目前的分布情况
        Map<String, Integer> areaMap = new HashMap<>();
        for (WmsAlleyDTO wmsAlley : computeParam.getGoodsAlley()) {
            String areaCode = wmsAlley.getAreaCode();
            areaMap.merge(areaCode, 1, Integer::sum);
        }
        //根据街区减权重
        emptyAlleys.forEach(data -> {
            Integer areaNum = areaMap.get(data.getAreaCode());
            if (areaNum != null) {
                data.setWeightNum(data.getWeightNum() - areaNum * AREA_BASE_NUM);
            }
        });

        //之前是初始化操作,之后开始循环查找巷道
        return loopSelectAlley(computeParam);
    }

    /**
     * 循环查找最终结果
     * @param computeParam
     */
    private List<WmsAlleyDTO> loopSelectAlley(ComputeParam computeParam) {
        int maxAlley = computeParam.getAlleyTypeAndNums().get(0).getStorageLocCount();
        List<WmsAlleyDTO> emptyAlleys = computeParam.getEmptyAlleys();
        List<WmsAlleyDTO> goodsAlley = computeParam.getGoodsAlley();

        //是否需要减左右库区权重
        boolean storageWeightNum = true;
        //根据左右库区减权重
        if (goodsAlley != null && goodsAlley.size() > 0) {
            //货物所属的一个库区
            String storage = goodsAlley.get(0).getStorage();
            //当仓库已存在本次货物时,判断此货物是否分布于两个库区,如果存在两个库区中就不需要减库区权重了
            for (WmsAlleyDTO wmsAlleyDTO : goodsAlley) {
                if (!wmsAlleyDTO.getStorage().equals(storage)) {
                    storageWeightNum = false;
                    break;
                }
            }
            if (storageWeightNum) {
                emptyAlleys.forEach(data -> {
                    if (storage.equals(data.getStorage())) {
                        data.setWeightNum(data.getWeightNum() - STORAGE_BASE_NUM);
                    }
                });
                storageWeightNum = false;
            }
        }
        //最终结果
        List<WmsAlleyDTO> needAlley = new ArrayList<>();
        //未分配的仓位数
        int remain = computeParam.getSum();

        while (remain > 0) {
            //获取权重最大的一个巷道
            WmsAlleyDTO wmsAlley = emptyAlleys.stream().max(Comparator.comparingInt(WmsAlleyDTO::getWeightNum)).get();
            //赋值需要新增货物的库位数
            if (remain >= wmsAlley.getStorageLocCount()) {
                wmsAlley.setNewGoodsNeed(wmsAlley.getStorageLocCount());
            } else {
                wmsAlley.setNewGoodsNeed(remain);
            }
            needAlley.add(wmsAlley);

            //根据左右库区减权重
            if (storageWeightNum) {
                emptyAlleys.forEach(data -> {
                    if (wmsAlley.getStorage().equals(data.getStorage())) {
                        data.setWeightNum(data.getWeightNum() - STORAGE_BASE_NUM);
                    }
                });
                storageWeightNum = false;
            }

            //把这个巷道从空巷道列表中移除,并添加到
            emptyAlleys.remove(wmsAlley);

            emptyAlleys.forEach(data -> {
                //根据区域减权重
                if (wmsAlley.getAreaCode().equals(data.getAreaCode())) {
                    data.setWeightNum(data.getWeightNum() - AREA_BASE_NUM);
                }
                //根据巷道减权重
                if (wmsAlley.getStorageLocCount().equals(data.getStorageLocCount())) {
                    data.setWeightNum(data.getWeightNum() - ADVISE_BASE_NUM);
                }
                //根据楼层减权重
                if (wmsAlley.getLayerCode().equals(data.getLayerCode())) {
                    data.setWeightNum(data.getWeightNum() - FLOOR_BASE_NUM);
                }
            });

            remain -= wmsAlley.getStorageLocCount();
            //TODO 最后一个巷道需要加一些处理防止库位浪费问题
            if (remain < maxAlley) {
                int finalRemain = remain;
                emptyAlleys.forEach(data -> {
                    //根据空余库位减权重
                    if (finalRemain < data.getStorageLocCount()) {
                        data.setWeightNum(data.getWeightNum() - (data.getStorageLocCount() - finalRemain) * REMAIN_BASE_NUM);
                    }
                });
            }
        }

        return needAlley;
    }


}
