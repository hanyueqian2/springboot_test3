package com.han.test.springboot_test3.utils.excel;

import com.github.crab2died.annotation.ExcelField;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Student {
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
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", classes=" + classes +
                '}';
    }
}
