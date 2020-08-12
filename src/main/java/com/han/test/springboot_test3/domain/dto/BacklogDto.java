package com.han.test.springboot_test3.domain.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 用于存储Student信息的类
 * @author han
 * @date 2018/11/27
 *
 */
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
    private Date leaveTime;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getBusinessNumber() {
        return businessNumber;
    }

    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getEnter() {
        return enter;
    }

    public void setEnter(String enter) {
        this.enter = enter;
    }
}
