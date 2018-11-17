package com.han.test.springboot_test3.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//用于存储Student信息的类
@Data
@Table(name = "backlog")
public class Backlog{
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column(name = "module")
    private String module;//订单模块

    @Column(name = "business_number")
    private String businessNumber;//业务单号

    @Column(name = "task")
    private String task;//任务

    @Column(name = "leave_time")
    private Long leaveTime;//离港日期

    @Column(name = "schedule")
    private String schedule;//进度

    @Column(name = "enter")
    private String enter;//详细信息网址

}
