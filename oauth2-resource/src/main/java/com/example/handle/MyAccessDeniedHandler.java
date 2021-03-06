package com.example.handle;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author：张鸿建
 * @time：2020/1/14 17:56
 * @desc：
 **/
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        System.out.println("权限拒绝异常:" + accessDeniedException.getMessage());
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        response.setStatus(503);
        writer.print(accessDeniedException.getMessage());
    }
}
