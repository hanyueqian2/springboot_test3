package com.han.test.springboot_test3.utils.excel;

import com.github.crab2died.annotation.ExcelField;

import java.util.Date;

public class Student implements Cloneable{
    @ExcelField(title = "学号", order = 1)
    private Long id;

    @ExcelField(title = "姓名", order = 2)
    private String name;

    @ExcelField(title = "入学日期", order = 3, writeConverter = DateConvertible.class)
    private Date date;

    @ExcelField(title = "班级", order = 4)
    private Integer classes;

    public Student(Long id, String name, Date date, Integer classes) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.classes = classes;
    }
    public Student(String name) {
        this.name = name;
    }

    public Student(){}

    @Override
    protected Student clone(){
        Student student = null;
        try {
            student = (Student) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return student;
    }


//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", classes=" + classes +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getClasses() {
        return classes;
    }

    public void setClasses(Integer classes) {
        this.classes = classes;
    }
}
