package com.han.test.springboot_test3.service.arithmetic;

/**
 * 巷道分量及数量
 */
public class AlleyTypeAndNum {
    /**
     * 巷道深度
     */
    private Integer storageLocCount;

    /**
     * 该类型巷道的数量
     */
    private Integer num;

    public Integer getStorageLocCount() {
        return storageLocCount;
    }

    public void setStorageLocCount(Integer storageLocCount) {
        this.storageLocCount = storageLocCount;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}

