package com.han.test.springboot_test3.domain.dto;

import javax.persistence.*;

@Table(name = "family_business_control")
public class FamilyBusinessControlDTO {
    @Id
    private Integer id;

    /**
     * 股票编号
     */
    @Column(name = "stock_num")
    private String stockNum;

    /**
     * 日期
     */
    @Column(name = "date_str")
    private String dateStr;

    /**
     * 人名
     */
    private String name;

    /**
     * 股权占比
     */
    @Column(name = "equity_ratio")
    private String equityRatio;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取股票编号
     *
     * @return stock_num - 股票编号
     */
    public String getStockNum() {
        return stockNum;
    }

    /**
     * 设置股票编号
     *
     * @param stockNum 股票编号
     */
    public void setStockNum(String stockNum) {
        this.stockNum = stockNum == null ? null : stockNum.trim();
    }

    /**
     * 获取日期
     *
     * @return date_str - 日期
     */
    public String getDateStr() {
        return dateStr;
    }

    /**
     * 设置日期
     *
     * @param dateStr 日期
     */
    public void setDateStr(String dateStr) {
        this.dateStr = dateStr == null ? null : dateStr.trim();
    }

    /**
     * 获取人名
     *
     * @return name - 人名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置人名
     *
     * @param name 人名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取股权占比
     *
     * @return equity_ratio - 股权占比
     */
    public String getEquityRatio() {
        return equityRatio;
    }

    /**
     * 设置股权占比
     *
     * @param equityRatio 股权占比
     */
    public void setEquityRatio(String equityRatio) {
        this.equityRatio = equityRatio == null ? null : equityRatio.trim();
    }
}