package com.han.test.springboot_test3.domain.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用于存储Student信息的类
 * @author han
 * @date 2018/11/27
 *
 */
@Data
@Table(name = "backlog")
public class BacklogDto{
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 订单模块
     */
    @Column(name = "module")
    private String module;

    /**
     * 业务单号
     */
    @Column(name = "business_number")
    private String businessNumber;

    /**
     * 任务
     */
    @Column(name = "task")
    private String task;

    /**
     * 离港日期
     */
    @Column(name = "leave_time")
    private Long leaveTime;

    /**
     * 进度
     */
    @Column(name = "schedule")
    private String schedule;

    /**
     * 详细信息网址
     */
    @Column(name = "enter")
    private String enter;

}
