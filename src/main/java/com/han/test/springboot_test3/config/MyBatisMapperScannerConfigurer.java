package com.han.test.springboot_test3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

@Configuration
public class MyBatisMapperScannerConfigurer {
    @Bean
    public static MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.wisdom.mapper.**");
        Properties properties = new Properties();
        properties.setProperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "MYSQL");
//        properties.setProperty("mappers", "com.wisdom.utils.mapper.MyMapper,com.wisdom.utils.mapper.MyMapperAndIds,com.wisdom.utils.mapper.LogicDelete.DeleteLogicMapper");
        mapperScannerConfigurer.setProperties(properties);

        return mapperScannerConfigurer;
    }

}
