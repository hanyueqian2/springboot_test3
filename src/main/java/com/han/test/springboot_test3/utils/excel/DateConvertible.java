package com.han.test.springboot_test3.utils.excel;

import com.github.crab2died.converter.WriteConvertible;
import com.github.crab2died.utils.DateUtils;

import java.util.Date;

public class DateConvertible implements WriteConvertible {
    /**
     * 导出excel日期数据转换器
     * @param date
     * @return
     */
    @Override
    public Object execWrite(Object date) {
        return DateUtils.date2Str((Date) date, DateUtils.DATE_FORMAT_SEC);
    }
}
