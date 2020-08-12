package com.han.test.springboot_test3.test;

import org.apache.ibatis.cache.impl.PerpetualCache;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MargeSqlString {
    @Test
    public void testMargeSqlString(){
        //SQL 语句
        String preparing = "select count(1) from fin_apply_open_invoice faoi left join fin_settlement fs on fs.settlement_id = faoi.settlement_id where fs.invoice_type in ( ? , ? ) and fs.delete_flag=0 and fs.apply_open_invoice_status = 1 and faoi.rejected_open_invoice_time is NULL and faoi.apply_open_invoice_status = 1 and faoi.open_type = 'FP' and fs.sys_code = ? and ( ( fs.created_by = ? or fs.created_by in ( ? ) and fs.dept in ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ) ) or fs.dept in ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ) )";
        //所有参数
        String parameters = "PP(String), P0(String), 1(Integer), 1(Integer), 1(Integer), 22(Integer), 10(Integer), 11(Integer), 12(Integer), 13(Integer), 14(Integer), 15(Integer), 16(Integer), 17(Integer), 18(Integer), 19(Integer), 20(Integer), 21(Integer), 23(Integer), 24(Integer), 25(Integer), 51(Integer), 22(Integer), 10(Integer), 11(Integer), 12(Integer), 13(Integer), 14(Integer), 15(Integer), 16(Integer), 17(Integer), 18(Integer), 19(Integer), 20(Integer), 21(Integer), 23(Integer), 24(Integer), 25(Integer), 51(Integer)";
        int i = 0;

        String[] parametersListTmp = parameters.split(",");
        List<String> parametersList = new ArrayList<>();
        for (String param : parametersListTmp) {
            param = param.trim();
            System.out.println(param);
            int start = param.indexOf("(");
            String type = param.substring(start + 1, param.length() - 1);
            String value = param.substring(0, start);
            if ("String".equals(type)) {
                parametersList.add("'" + value + "'");
            } else if ("Integer".equals(type)) {
                parametersList.add(value);
            } else {
                parametersList.add(value);
            }
        }

        //向SQL中添加所有的参数
        while (preparing.contains("?")) {
            preparing = preparing.replaceFirst("[?]", parametersList.get(i++));

        }
        //输出结果
        System.out.println(preparing);

    }
}
