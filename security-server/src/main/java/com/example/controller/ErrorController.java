package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author：张鸿建
 * @time：2019/12/3 15:17
 * @desc：
 **/
@RestController
public class ErrorController {
    @RequestMapping("/error/403")
    public String error() {
        return "/error/403";
    }
}
