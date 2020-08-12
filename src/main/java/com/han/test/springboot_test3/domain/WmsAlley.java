package com.han.test.springboot_test3.domain;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "wms_alley")
public class WmsAlley {
    @Id
    @Column(name = "alley_code")
    private String alleyCode;

    /**
     * 仓库号
     */
    @Column(name = "wh_code")
    private String whCode;

    /**
     * 库区Code
     */
    @Column(name = "stg_typ_code")
    private String stgTypCode;

    /**
     * 巷道描述
     */
    @Column(name = "alley_text")
    private String alleyText;

    /**
     * 开口方向
     */
    @Column(name = "open_dir")
    private String openDir;

    /**
     * 临时锁定（0-未锁定，1-锁定）
     */
    @Column(name = "temp_lock")
    private String tempLock;

    /**
     * 冻结原因
     */
    @Column(name = "blk_rea_code")
    private String blkReaCode;

    /**
     * 冻结人
     */
    @Column(name = "blk_user")
    private String blkUser;

    /**
     * 出库冻结标识（用户）
     */
    @Column(name = "ind_blk_rm_u")
    private String indBlkRmU;

    /**
     * 入库冻结标识（用户）
     */
    @Column(name = "ind_blk_pm_u")
    private String indBlkPmU;

    /**
     * X坐标
     */
    @Column(name = "coo_x")
    private BigDecimal cooX;

    /**
     * Y坐标
     */
    @Column(name = "coo_y")
    private BigDecimal cooY;

    /**
     * X坐标终值
     */
    @Column(name = "x_end")
    private BigDecimal xEnd;

    /**
     * Z坐标
     */
    @Column(name = "coo_z")
    private BigDecimal cooZ;

    /**
     * 地图编号
     */
    @Column(name = "map_code")
    private String mapCode;

    /**
     * 层级编号
     */
    @Column(name = "layer_code")
    private Integer layerCode;

    /**
     * AGV存储（0-人工库,1-AGV库,2-立库）
     */
    @Column(name = "agv_stg")
    private Short agvStg;

    /**
     * 混放策略编号
     */
    @Column(name = "alley_mix_strategy_code")
    private String alleyMixStrategyCode;

    /**
     * 冷热度指数
     */
    @Column(name = "hot_index")
    private Integer hotIndex;

    /**
     * 自定义字符串1
     */
    @Column(name = "alley_str1")
    private String alleyStr1;

    /**
     * 自定义字符串2
     */
    @Column(name = "alley_str2")
    private String alleyStr2;

    /**
     * 自定义字符串1
     */
    @Column(name = "alley_str3")
    private String alleyStr3;

    /**
     * 自定义字符串2
     */
    @Column(name = "alley_str4")
    private String alleyStr4;

    /**
     * 自定义字符串1
     */
    @Column(name = "alley_str5")
    private String alleyStr5;

    /**
     * 库位个数
     */
    @Column(name = "storage_loc_count")
    private Integer storageLocCount;

    /**
     * 权重
     */
    @Column(name = "weight_num")
    private Integer weightNum;

    /**
     * 出口通道x坐标
     */
    @Column(name = "exit_coo_x")
    private String exitCooX;
    /**
     * 是否有货
     */
    @Column(name = "goods")
    private String goods;
    /**
     * 货物种类
     */
    @Column(name = "goods_type")
    private String goodsType;
    /**
     * 所属区域(街区)
     */
    @Column(name = "area_code")
    private String areaCode;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    /**
     * @return alley_code
     */
    public String getAlleyCode() {
        return alleyCode;
    }

    /**
     * @param alleyCode
     */
    public void setAlleyCode(String alleyCode) {
        this.alleyCode = alleyCode == null ? null : alleyCode.trim();
    }

    /**
     * 获取仓库号
     *
     * @return wh_code - 仓库号
     */
    public String getWhCode() {
        return whCode;
    }

    /**
     * 设置仓库号
     *
     * @param whCode 仓库号
     */
    public void setWhCode(String whCode) {
        this.whCode = whCode == null ? null : whCode.trim();
    }

    /**
     * 获取库区Code
     *
     * @return stg_typ_code - 库区Code
     */
    public String getStgTypCode() {
        return stgTypCode;
    }

    /**
     * 设置库区Code
     *
     * @param stgTypCode 库区Code
     */
    public void setStgTypCode(String stgTypCode) {
        this.stgTypCode = stgTypCode == null ? null : stgTypCode.trim();
    }

    /**
     * 获取巷道描述
     *
     * @return alley_text - 巷道描述
     */
    public String getAlleyText() {
        return alleyText;
    }

    /**
     * 设置巷道描述
     *
     * @param alleyText 巷道描述
     */
    public void setAlleyText(String alleyText) {
        this.alleyText = alleyText == null ? null : alleyText.trim();
    }

    /**
     * 获取开口方向
     *
     * @return open_dir - 开口方向
     */
    public String getOpenDir() {
        return openDir;
    }

    /**
     * 设置开口方向
     *
     * @param openDir 开口方向
     */
    public void setOpenDir(String openDir) {
        this.openDir = openDir == null ? null : openDir.trim();
    }

    /**
     * 获取临时锁定（0-未锁定，1-锁定）
     *
     * @return temp_lock - 临时锁定（0-未锁定，1-锁定）
     */
    public String getTempLock() {
        return tempLock;
    }

    /**
     * 设置临时锁定（0-未锁定，1-锁定）
     *
     * @param tempLock 临时锁定（0-未锁定，1-锁定）
     */
    public void setTempLock(String tempLock) {
        this.tempLock = tempLock == null ? null : tempLock.trim();
    }

    /**
     * 获取冻结原因
     *
     * @return blk_rea_code - 冻结原因
     */
    public String getBlkReaCode() {
        return blkReaCode;
    }

    /**
     * 设置冻结原因
     *
     * @param blkReaCode 冻结原因
     */
    public void setBlkReaCode(String blkReaCode) {
        this.blkReaCode = blkReaCode == null ? null : blkReaCode.trim();
    }

    /**
     * 获取冻结人
     *
     * @return blk_user - 冻结人
     */
    public String getBlkUser() {
        return blkUser;
    }

    /**
     * 设置冻结人
     *
     * @param blkUser 冻结人
     */
    public void setBlkUser(String blkUser) {
        this.blkUser = blkUser == null ? null : blkUser.trim();
    }

    /**
     * 获取出库冻结标识（用户）
     *
     * @return ind_blk_rm_u - 出库冻结标识（用户）
     */
    public String getIndBlkRmU() {
        return indBlkRmU;
    }

    /**
     * 设置出库冻结标识（用户）
     *
     * @param indBlkRmU 出库冻结标识（用户）
     */
    public void setIndBlkRmU(String indBlkRmU) {
        this.indBlkRmU = indBlkRmU == null ? null : indBlkRmU.trim();
    }

    /**
     * 获取入库冻结标识（用户）
     *
     * @return ind_blk_pm_u - 入库冻结标识（用户）
     */
    public String getIndBlkPmU() {
        return indBlkPmU;
    }

    /**
     * 设置入库冻结标识（用户）
     *
     * @param indBlkPmU 入库冻结标识（用户）
     */
    public void setIndBlkPmU(String indBlkPmU) {
        this.indBlkPmU = indBlkPmU == null ? null : indBlkPmU.trim();
    }

    /**
     * 获取X坐标
     *
     * @return coo_x - X坐标
     */
    public BigDecimal getCooX() {
        return cooX;
    }

    /**
     * 设置X坐标
     *
     * @param cooX X坐标
     */
    public void setCooX(BigDecimal cooX) {
        this.cooX = cooX;
    }

    /**
     * 获取Y坐标
     *
     * @return coo_y - Y坐标
     */
    public BigDecimal getCooY() {
        return cooY;
    }

    /**
     * 设置Y坐标
     *
     * @param cooY Y坐标
     */
    public void setCooY(BigDecimal cooY) {
        this.cooY = cooY;
    }

    /**
     * 获取X坐标终值
     *
     * @return x_end - X坐标终值
     */
    public BigDecimal getxEnd() {
        return xEnd;
    }

    /**
     * 设置X坐标终值
     *
     * @param xEnd X坐标终值
     */
    public void setxEnd(BigDecimal xEnd) {
        this.xEnd = xEnd;
    }

    /**
     * 获取Z坐标
     *
     * @return coo_z - Z坐标
     */
    public BigDecimal getCooZ() {
        return cooZ;
    }

    /**
     * 设置Z坐标
     *
     * @param cooZ Z坐标
     */
    public void setCooZ(BigDecimal cooZ) {
        this.cooZ = cooZ;
    }

    /**
     * 获取地图编号
     *
     * @return map_code - 地图编号
     */
    public String getMapCode() {
        return mapCode;
    }

    /**
     * 设置地图编号
     *
     * @param mapCode 地图编号
     */
    public void setMapCode(String mapCode) {
        this.mapCode = mapCode == null ? null : mapCode.trim();
    }

    /**
     * 获取层级编号
     *
     * @return layer_code - 层级编号
     */
    public Integer getLayerCode() {
        return layerCode;
    }

    /**
     * 设置层级编号
     *
     * @param layerCode 层级编号
     */
    public void setLayerCode(Integer layerCode) {
        this.layerCode = layerCode;
    }

    /**
     * 获取AGV存储（0-人工库,1-AGV库,2-立库）
     *
     * @return agv_stg - AGV存储（0-人工库,1-AGV库,2-立库）
     */
    public Short getAgvStg() {
        return agvStg;
    }

    /**
     * 设置AGV存储（0-人工库,1-AGV库,2-立库）
     *
     * @param agvStg AGV存储（0-人工库,1-AGV库,2-立库）
     */
    public void setAgvStg(Short agvStg) {
        this.agvStg = agvStg;
    }

    /**
     * 获取混放策略编号
     *
     * @return alley_mix_strategy_code - 混放策略编号
     */
    public String getAlleyMixStrategyCode() {
        return alleyMixStrategyCode;
    }

    /**
     * 设置混放策略编号
     *
     * @param alleyMixStrategyCode 混放策略编号
     */
    public void setAlleyMixStrategyCode(String alleyMixStrategyCode) {
        this.alleyMixStrategyCode = alleyMixStrategyCode == null ? null : alleyMixStrategyCode.trim();
    }

    /**
     * 获取冷热度指数
     *
     * @return hot_index - 冷热度指数
     */
    public Integer getHotIndex() {
        return hotIndex;
    }

    /**
     * 设置冷热度指数
     *
     * @param hotIndex 冷热度指数
     */
    public void setHotIndex(Integer hotIndex) {
        this.hotIndex = hotIndex;
    }

    /**
     * 获取自定义字符串1
     *
     * @return alley_str1 - 自定义字符串1
     */
    public String getAlleyStr1() {
        return alleyStr1;
    }

    /**
     * 设置自定义字符串1
     *
     * @param alleyStr1 自定义字符串1
     */
    public void setAlleyStr1(String alleyStr1) {
        this.alleyStr1 = alleyStr1 == null ? null : alleyStr1.trim();
    }

    /**
     * 获取自定义字符串2
     *
     * @return alley_str2 - 自定义字符串2
     */
    public String getAlleyStr2() {
        return alleyStr2;
    }

    /**
     * 设置自定义字符串2
     *
     * @param alleyStr2 自定义字符串2
     */
    public void setAlleyStr2(String alleyStr2) {
        this.alleyStr2 = alleyStr2 == null ? null : alleyStr2.trim();
    }

    /**
     * 获取自定义字符串1
     *
     * @return alley_str3 - 自定义字符串1
     */
    public String getAlleyStr3() {
        return alleyStr3;
    }

    /**
     * 设置自定义字符串1
     *
     * @param alleyStr3 自定义字符串1
     */
    public void setAlleyStr3(String alleyStr3) {
        this.alleyStr3 = alleyStr3 == null ? null : alleyStr3.trim();
    }

    /**
     * 获取自定义字符串2
     *
     * @return alley_str4 - 自定义字符串2
     */
    public String getAlleyStr4() {
        return alleyStr4;
    }

    /**
     * 设置自定义字符串2
     *
     * @param alleyStr4 自定义字符串2
     */
    public void setAlleyStr4(String alleyStr4) {
        this.alleyStr4 = alleyStr4 == null ? null : alleyStr4.trim();
    }

    /**
     * 获取自定义字符串1
     *
     * @return alley_str5 - 自定义字符串1
     */
    public String getAlleyStr5() {
        return alleyStr5;
    }

    /**
     * 设置自定义字符串1
     *
     * @param alleyStr5 自定义字符串1
     */
    public void setAlleyStr5(String alleyStr5) {
        this.alleyStr5 = alleyStr5 == null ? null : alleyStr5.trim();
    }

    /**
     * 获取库位个数
     *
     * @return storage_loc_count - 库位个数
     */
    public Integer getStorageLocCount() {
        return storageLocCount;
    }

    /**
     * 设置库位个数
     *
     * @param storageLocCount 库位个数
     */
    public void setStorageLocCount(Integer storageLocCount) {
        this.storageLocCount = storageLocCount;
    }

    /**
     * 获取权重
     *
     * @return weight_num - 权重
     */
    public Integer getWeightNum() {
        return weightNum;
    }

    /**
     * 设置权重
     *
     * @param weightNum 权重
     */
    public void setWeightNum(Integer weightNum) {
        this.weightNum = weightNum;
    }

    /**
     * 获取出口通道x坐标
     *
     * @return exit_coo_x - 出口通道x坐标
     */
    public String getExitCooX() {
        return exitCooX;
    }

    /**
     * 设置出口通道x坐标
     *
     * @param exitCooX 出口通道x坐标
     */
    public void setExitCooX(String exitCooX) {
        this.exitCooX = exitCooX == null ? null : exitCooX.trim();
    }
}