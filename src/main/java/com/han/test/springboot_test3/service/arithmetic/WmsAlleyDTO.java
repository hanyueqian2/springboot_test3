package com.han.test.springboot_test3.service.arithmetic;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Table(name = "wms_alley")
public class WmsAlleyDTO {
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
     *v开口方向
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
     * YY坐标
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
     * 自定义字符串3
     */
    @Column(name = "alley_str3")
    private String alleyStr3;

    /**
     * 自定义字符串4
     */
    @Column(name = "alley_str4")
    private String alleyStr4;

    /**
     * 自定义字符串5
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
    private Integer weightNum;
    /**
     * 所属区域(街区)
     */
    private String areaCode;

    /**
     * 所属仓库(左右库)
     */
    private String storage;

    /**
     * 货物占用个数
     */
    private Integer goodsNum;
    /**
     * 需要新增货物的库位数
     */
    private Integer newGoodsNeed;

    /**
     * 货物种类
     */
    @Column(name = "goods_type")
    private String goodsType;

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

    public String getExitCooX() {
        return exitCooX;
    }

    public void setExitCooX(String exitCooX) {
        this.exitCooX = exitCooX;
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

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Integer getNewGoodsNeed() {
        return newGoodsNeed;
    }

    public void setNewGoodsNeed(Integer newGoodsNeed) {
        this.newGoodsNeed = newGoodsNeed;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public Integer getWeightNum() {
        return weightNum;
    }

    public void setWeightNum(Integer weightNum) {
        this.weightNum = weightNum;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
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

    public String getWhCode() {
        return whCode;
    }

    public void setWhCode(String whCode) {
        this.whCode = whCode == null ? null : whCode.trim();
    }

    public String getStgTypCode() {
        return stgTypCode;
    }

    public void setStgTypCode(String stgTypCode) {
        this.stgTypCode = stgTypCode == null ? null : stgTypCode.trim();
    }

    public String getAlleyText() {
        return alleyText;
    }

    public void setAlleyText(String alleyText) {
        this.alleyText = alleyText == null ? null : alleyText.trim();
    }

    public String getOpenDir() {
        return openDir;
    }

    public void setOpenDir(String openDir) {
        this.openDir = openDir == null ? null : openDir.trim();
    }

    public String getTempLock() {
        return tempLock;
    }

    public void setTempLock(String tempLock) {
        this.tempLock = tempLock == null ? null : tempLock.trim();
    }

    public String getBlkReaCode() {
        return blkReaCode;
    }

    public void setBlkReaCode(String blkReaCode) {
        this.blkReaCode = blkReaCode == null ? null : blkReaCode.trim();
    }

    public String getBlkUser() {
        return blkUser;
    }

    public void setBlkUser(String blkUser) {
        this.blkUser = blkUser == null ? null : blkUser.trim();
    }

    public String getIndBlkRmU() {
        return indBlkRmU;
    }

    public void setIndBlkRmU(String indBlkRmU) {
        this.indBlkRmU = indBlkRmU == null ? null : indBlkRmU.trim();
    }

    public String getIndBlkPmU() {
        return indBlkPmU;
    }

    public void setIndBlkPmU(String indBlkPmU) {
        this.indBlkPmU = indBlkPmU == null ? null : indBlkPmU.trim();
    }

    public BigDecimal getCooX() {
        return cooX;
    }

    public void setCooX(BigDecimal cooX) {
        this.cooX = cooX;
    }

    public BigDecimal getCooY() {
        return cooY;
    }

    public void setCooY(BigDecimal cooY) {
        this.cooY = cooY;
    }

    public BigDecimal getxEnd() {
        return xEnd;
    }

    public void setxEnd(BigDecimal xEnd) {
        this.xEnd = xEnd;
    }

    public BigDecimal getCooZ() {
        return cooZ;
    }

    public void setCooZ(BigDecimal cooZ) {
        this.cooZ = cooZ;
    }

    public String getMapCode() {
        return mapCode;
    }

    public void setMapCode(String mapCode) {
        this.mapCode = mapCode == null ? null : mapCode.trim();
    }

    public Integer getLayerCode() {
        return layerCode;
    }

    public void setLayerCode(Integer layerCode) {
        this.layerCode = layerCode;
    }

    public Short getAgvStg() {
        return agvStg;
    }

    public void setAgvStg(Short agvStg) {
        this.agvStg = agvStg;
    }

    public String getAlleyMixStrategyCode() {
        return alleyMixStrategyCode;
    }

    public void setAlleyMixStrategyCode(String alleyMixStrategyCode) {
        this.alleyMixStrategyCode = alleyMixStrategyCode == null ? null : alleyMixStrategyCode.trim();
    }

    public Integer getHotIndex() {
        return hotIndex;
    }

    public void setHotIndex(Integer hotIndex) {
        this.hotIndex = hotIndex;
    }

    public String getAlleyStr1() {
        return alleyStr1;
    }

    public void setAlleyStr1(String alleyStr1) {
        this.alleyStr1 = alleyStr1 == null ? null : alleyStr1.trim();
    }

    public String getAlleyStr2() {
        return alleyStr2;
    }

    public void setAlleyStr2(String alleyStr2) {
        this.alleyStr2 = alleyStr2 == null ? null : alleyStr2.trim();
    }

    public String getAlleyStr3() {
        return alleyStr3;
    }

    public void setAlleyStr3(String alleyStr3) {
        this.alleyStr3 = alleyStr3 == null ? null : alleyStr3.trim();
    }

    public String getAlleyStr4() {
        return alleyStr4;
    }

    public void setAlleyStr4(String alleyStr4) {
        this.alleyStr4 = alleyStr4 == null ? null : alleyStr4.trim();
    }

    public String getAlleyStr5() {
        return alleyStr5;
    }

    public void setAlleyStr5(String alleyStr5) {
        this.alleyStr5 = alleyStr5 == null ? null : alleyStr5.trim();
    }

    public Integer getStorageLocCount() {
        return storageLocCount;
    }

    public void setStorageLocCount(Integer storageLocCount) {
        this.storageLocCount = storageLocCount;
    }
}