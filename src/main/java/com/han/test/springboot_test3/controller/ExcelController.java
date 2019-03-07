package com.han.test.springboot_test3.controller;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.IgnoredErrorType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
@ResponseBody
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
    }

    public static boolean isRowEmpty(XSSFRow row) {

        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && !CellType.BLANK.equals(cell.getCellTypeEnum()) && !CellType.ERROR.equals(cell.getCellTypeEnum())) {
                return false;
            }
        }
        return true;
    }

    @GetMapping("/excelDownload")
    public String excelDownload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/force-download");
        Resource resource = new ClassPathResource("excel/employee_import_template.xlsx");
        String dir = resource.getFile().getPath();
        String fileName = resource.getFilename();
        response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
        Files.copy(Paths.get(dir), response.getOutputStream());
//        if (file.exists()) {
////            response.setContentType("application/force-download");
////            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
////            byte[] bytes = new byte[1024];
////            FileInputStream fileInputStream = null;
////            BufferedInputStream bufferedInputStream = null;
////            try {
////                fileInputStream = new FileInputStream(file);
////                bufferedInputStream = new BufferedInputStream(fileInputStream);
////                ServletOutputStream outputStream = response.getOutputStream();
////                Integer totalNum = bufferedInputStream.read(bytes);
////                while (totalNum != -1) {
////                    outputStream.write(bytes, 0, bytes.length);
////                    outputStream.flush();
////                    totalNum = bufferedInputStream.read(bytes);
////                }
////            } catch (FileNotFoundException e) {
////                e.printStackTrace();
////                return "找不到文件";
////            } catch (IOException e) {
////                e.printStackTrace();
////            } finally {
////                if (bufferedInputStream != null) {
////                    try {
////                        bufferedInputStream.close();
////                    } catch (IOException e) {
////                        e.printStackTrace();
////                    }
////                }
////                if (fileInputStream != null) {
////                    try {
////                        fileInputStream.close();
////                    } catch (IOException e) {
////                        e.printStackTrace();
////                    }
////                }
////            }
////        } else {
////            return "文件不存在";
////        }
        return "下载成功";

    }
}

