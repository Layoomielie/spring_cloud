package com.example.controller;/**
 * ${tag}
 *
 * @author zhanghongjian
 * @Date 2019/6/21 19:26
 */

import com.example.service.FreeMarkerService;
import com.example.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author：张鸿建
 * @time：2019/6/21
 * @desc：
 **/
@Controller
@RequestMapping(value = "/ftl")
public class FreeMarkerCtrl {

    @Autowired
    FreeMarkerService freeMarkerService;

    @RequestMapping(value = "/index")
    public String index(ModelMap map){
        Resource resource = new Resource();
        resource.setName("ali");
        resource.setWebsite("www.baidu.com");
        resource.setLanguage("python");
        map.addAttribute("resource",resource);
        return "freemarker/index";
    }

    @RequestMapping(value = "/create")
    public String index(){
        freeMarkerService.writefile();
        return "sucess";
    }
}
