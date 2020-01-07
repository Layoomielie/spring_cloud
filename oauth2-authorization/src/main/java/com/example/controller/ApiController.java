package com.example.controller;

import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
 * @author：张鸿建
 * @time：2019/12/17 10:31
 * @desc：
 **/
@RestController
@RequestMapping("/api")
public class ApiController {

    @RequestMapping("/hi")
    public String getAuthCode(){
        System.out.println("hello world");
        return "auth sucess";
    }
    @RequestMapping("/good")
    public String getGood(){
        System.out.println("hello world");
        return "auth sucess";
    }
    @GetMapping("/token")
    public Object index(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = StringUtils.substringAfter(header, "bearer ");

        return Jwts.parser().setSigningKey("test_key".getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token).getBody();
    }
}
