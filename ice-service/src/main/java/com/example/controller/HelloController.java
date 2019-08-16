package com.example.controller;

import com.example.entity.GoodEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：张鸿建
 * @time：2019/8/16 16:20
 * @desc：
 **/
@RestController
public class HelloController {

    @CrossOrigin(origins = "*")
    @RequestMapping("test")
    public List<GoodEntity> getData(){
        GoodEntity good1 = new GoodEntity("ali1", "10", "2018-01-01");
        GoodEntity good2 = new GoodEntity("ali2", "10", "2018-01-01");
        GoodEntity good3 = new GoodEntity("ali3", "10", "2018-01-01");
        List<GoodEntity> lists = new ArrayList<>();
        lists.add(good1);
        lists.add(good2);
        lists.add(good3);
        return lists;
    }
}
