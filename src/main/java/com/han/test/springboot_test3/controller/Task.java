package com.han.test.springboot_test3.controller;


import org.apache.commons.configuration2.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

@Component
public class Task {

    private String VERSION = "version";



    /**
     * 处理任务信息异常
     *
     */
//    @Scheduled(fixedRateString="60000",initialDelayString="100")
    public void disposeError() {

        PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration();

        Properties upgradeLog = new Properties();
        Properties service = new Properties();
        InputStream upgradeLogStream = null;
        InputStream serviceStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            upgradeLogStream = new ClassPathResource("static/check/upgradeLog.properties").getInputStream();
            serviceStream = new ClassPathResource("static/service/server.properties").getInputStream();
            upgradeLog.load(upgradeLogStream);
            service.load(serviceStream);

            String currentVersion = upgradeLog.getProperty(VERSION);
            String preVersion = service.getProperty(VERSION);

            System.out.println("=========================================");
            if (currentVersion.equals(preVersion)) {
                System.out.println(">>>>>>>>>>>>>>>>>>项目重启成功,版本号:" + currentVersion);
            } else {
                System.out.println(">>>>>>>>>>>>>>>>>>项目启动成功,当前版本号:" + currentVersion + ",更新前版本号:"+  preVersion);
                service.setProperty(VERSION, currentVersion);
                String path = Task.class.getClassLoader().getResource("static/service/server.properties").getPath();
                fileOutputStream = new FileOutputStream(path);
                service.store(fileOutputStream, "Update value");
            }
            System.out.println("=========================================");


//            Set<String> names = upgradeLog.stringPropertyNames();
//            for (String name : names) {
//                System.out.println(name);
//                String value = upgradeLog.getProperty(name);
//                System.out.println(value);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (upgradeLogStream != null) {
                    upgradeLogStream.close();
                }
                if (serviceStream != null) {
                    serviceStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


//        String path = null;
//        try {
//            path = ResourceUtils.getURL("classpath:").getPath() + "static" + "/service";
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        System.out.println(path);
//        File filePath = new File(path);
//        if (!filePath.exists()) {
//            boolean mkdirs = filePath.mkdirs();
//            if (mkdirs) {
//                System.out.println("创建路径成功");
//            } else {
//                System.out.println("创建路径失败");
//            }
//        }
//
//        File file1 = new File("target/classes/static/check");
//        if (!file1.exists()) {
//            boolean mkdirs = false;
//            mkdirs = file1.mkdirs();
//            if (mkdirs) {
//                System.out.println("创建路径成功");
//            } else {
//                System.out.println("创建路径失败");
//            }
//        }
//
//        File file = new File(path + "/server.properties");
//        if (!file.exists()) {
//            boolean mkdirs = false;
//            try {
//                mkdirs = file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            if (mkdirs) {
//                System.out.println("创建路径成功");
//            } else {
//                System.out.println("创建路径失败");
//            }
//        }

//        Properties hello = new Properties();
//
//        try {
//            InputStream in = this.getClass().getResourceAsStream("hello.properties");
//            hello.load(in);
//            Iterator<String> it = hello.stringPropertyNames().iterator();
//            while(it.hasNext()){
//                String key=it.next();
//                System.out.println(key+":"+hello.getProperty(key));
//            }
//            in.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }


}
