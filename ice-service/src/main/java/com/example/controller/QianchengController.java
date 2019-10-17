package com.example.controller;

import com.example.dao.QianchengDao;
import com.example.entity.Qiancheng;
import com.example.entity.QianchengExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author：张鸿建
 * @time：2019/8/19 13:41
 * @desc：
 **/
@RestController
public class QianchengController {


    @Autowired(required = true)
    private QianchengDao qianchengDao;

    @CrossOrigin(origins = "*")
    @GetMapping("query/city")
    public List<Qiancheng>  queryCity(String city,int page,int size){
        QianchengExample qianchengExample = new QianchengExample();
        QianchengExample.Criteria criteria = qianchengExample.createCriteria();
        criteria.andCityEqualTo(city);
        qianchengExample.setLimit(size);
        qianchengExample.setOffset(new Long(page));
        List<Qiancheng> qianchengs = qianchengDao.selectByExample(qianchengExample);
        return qianchengs;
    }
 }
