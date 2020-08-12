package com.han.test.springboot_test3.utils.excel;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class HttpRequestUtils {
    public String soapRequest(Student student) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/xml; charset=UTF-8");

        HttpEntity<Student> requestEntity = new HttpEntity<>(student, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange("http://127.0.0.1/", HttpMethod.POST, requestEntity, String.class);

        List<String> val = resp.getHeaders().get("Set-Cookie");
        System.out.println(val);

        String body = resp.getBody();
        System.out.println(body);
        return body;
    }
}
