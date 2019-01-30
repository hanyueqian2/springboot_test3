package com.han.test.springboot_test3.controller;

import jdk.nashorn.internal.ir.ReturnNode;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.ForkJoinPool;

@Controller
public class ExcelController {

    @PostMapping("/excelImport")
    public void excelImport(@RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
//        XSSFWorkbook excel = new XSSFWorkbook(file.getInputStream());
        HSSFWorkbook excel = new HSSFWorkbook(file.getInputStream());
        HSSFSheet sheet = excel.getSheetAt(0);

        int lastRowNum = sheet.getLastRowNum() + 1;
        for (int i = 0;
             i < lastRowNum;
             i++) {
            HSSFRow row = sheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++) {
                HSSFCell cell = row.getCell(j);
                String rawValue = cell.getStringCellValue();
//                XSSFCell cell = row.getCell(j);
//                String rawValue = cell.getRawValue();
                System.out.println(rawValue);
            }

        }
//        return "成功";
    }
}

