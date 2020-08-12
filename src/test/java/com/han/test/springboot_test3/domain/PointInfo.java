package com.han.test.springboot_test3.domain;

public class PointInfo {
    /**
     * 点位编号
     */
    private String berCode;
    /**
     * 楼层
     */
    private String layerCode;
    /**
     * 行
     */
    private String cooY;
    /**
     * 列
     */
    private String cooX;
    /**
     * 点位类型
     */
    private String berth_type;

    public String getBerCode() {
        return berCode;
    }

    public void setBerCode(String berCode) {
        this.berCode = berCode;
    }

    public String getLayerCode() {
        return layerCode;
    }

    public void setLayerCode(String layerCode) {
        this.layerCode = layerCode;
    }

    public String getCooY() {
        return cooY;
    }

    public void setCooY(String cooY) {
        this.cooY = cooY;
    }

    public String getCooX() {
        return cooX;
    }

    public void setCooX(String cooX) {
        this.cooX = cooX;
    }

    public String getBerth_type() {
        return berth_type;
    }

    public void setBerth_type(String berth_type) {
        this.berth_type = berth_type;
    }
}
