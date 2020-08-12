package com.han.test.springboot_test3.service.arithmetic;

import java.util.List;

/**
 * 计算巷道的参数
 */
public class ComputeParam {
    /**
     * 货物类型
     */
    private String goodsType;
    /**
     * 货物热度
     */
    private Integer goodsHot;

    /**
     * 需要的总仓位
     */
    private Integer sum;

    /**
     * 查出所有的空巷道
     */
    private List<WmsAlleyDTO> emptyAlleys;
    /**
     * 查出所有含有当前货物的巷道(包括不满的)
     */
    private List<WmsAlleyDTO> goodsAlley;
    /**
     * 查出所有当前货物的非满巷道
     */
    private List<WmsAlleyDTO> notFullAlleys;
    /**
     * 查找巷道类型,和数量
     */
    private List<AlleyTypeAndNum> alleyTypeAndNums;

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public Integer getGoodsHot() {
        return goodsHot;
    }

    public void setGoodsHot(Integer goodsHot) {
        this.goodsHot = goodsHot;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public List<WmsAlleyDTO> getEmptyAlleys() {
        return emptyAlleys;
    }

    public void setEmptyAlleys(List<WmsAlleyDTO> emptyAlleys) {
        this.emptyAlleys = emptyAlleys;
    }

    public List<WmsAlleyDTO> getGoodsAlley() {
        return goodsAlley;
    }

    public void setGoodsAlley(List<WmsAlleyDTO> goodsAlley) {
        this.goodsAlley = goodsAlley;
    }

    public List<AlleyTypeAndNum> getAlleyTypeAndNums() {
        return alleyTypeAndNums;
    }

    public void setAlleyTypeAndNums(List<AlleyTypeAndNum> alleyTypeAndNums) {
        this.alleyTypeAndNums = alleyTypeAndNums;
    }

    public List<WmsAlleyDTO> getNotFullAlleys() {
        return notFullAlleys;
    }

    public void setNotFullAlleys(List<WmsAlleyDTO> notFullAlleys) {
        this.notFullAlleys = notFullAlleys;
    }
}
