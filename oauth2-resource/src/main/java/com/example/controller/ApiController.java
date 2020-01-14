package com.example.controller;

import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Principal;

/**
 * @author：张鸿建
 * @time：2019/12/17 10:31
 * @desc：
 **/
@RestController
@RequestMapping("/api")
public class ApiController {

    @RequestMapping("/hi")
    @PreAuthorize("hasRole('QAQ')")
    public String getAuthCode() {
        System.out.println("auth sucess");
        return "auth sucess";
    }

    @GetMapping("/token")
    public Object index(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = StringUtils.substringAfter(header, "bearer");
        return Jwts.parser().setSigningKey("test_key".getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token).getBody();
    }

    @GetMapping("/admin/info")
    @PreAuthorize("hasAuthority('XXX')")
    public Object getAdminInfo(HttpServletRequest request) {
        String msg = "当前用户有admin角色";
        System.out.println(msg);
        return msg;
    }


    @GetMapping("/user/info")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Principal user(Principal user) {
        return user;
    }
}
