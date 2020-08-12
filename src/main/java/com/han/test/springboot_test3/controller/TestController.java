package com.han.test.springboot_test3.controller;

import com.han.test.springboot_test3.domain.Backlog;
import com.han.test.springboot_test3.service.RedisUtils;
import com.han.test.springboot_test3.utils.excel.Student;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping(value = "/test", produces = "application/json;charset=UTF-8")
@ResponseBody
public class TestController {

    @Autowired
    RedisUtils redisUtils;

    @GetMapping("/testRedis")
    public String testRedis() {
        String name = "xia";
        Backlog backlog = new Backlog();
        backlog.setId(1L);
        backlog.setSchedule("schedule");
        backlog.setTask("M123456");
        backlog.setEnter("enter");
        redisUtils.set("name", backlog, 10);
        Backlog redisObject = (Backlog)redisUtils.get("name");
        return redisObject.getEnter();
    }

    @PostMapping("/testXml")
    public String testXml(@RequestBody Map<String,String> map) {
        System.out.println(map.get("param1"));
        System.out.println(map.get("param2"));
        System.out.println(map.get("param3"));

        return "OK";
    }
//    @PostMapping(value = "/testXml2", produces="application/xml")
//    public TestXml testXml2() {
//        TestXml testXml = new TestXml();
//        testXml.setParam1("aaa");
//        testXml.setParam2("bbb");
//        testXml.setParam3("ccc");
//        System.out.println(testXml.getParam1());
//        System.out.println(testXml.getParam2());
//        System.out.println(testXml.getParam3());
//
//        return testXml;
//    }
//    @Getter
//    @Setter
//    @JacksonXmlRootElement(localName = "root")
//    public class TestXml {
//        @JacksonXmlProperty(localName = "param1")
//        private String param1;
//        @JacksonXmlProperty(localName = "param2")
//        private String param2;
//        @JacksonXmlProperty(localName = "param3")
//        private String param3;
//    }


}
