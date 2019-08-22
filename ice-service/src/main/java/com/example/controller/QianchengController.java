package com.example.controller;

import com.example.entity.PositionEntity;
import com.example.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author：张鸿建
 * @time：2019/8/19 13:41
 * @desc：
 **/
@RestController
public class QianchengController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private PositionRepository positionRepository;

    @CrossOrigin(origins = "*")
    @RequestMapping("query")
    public Object query(){
        long count = positionRepository.count();
        Query query = new Query(Criteria.where("city").is("西安"));
        List<PositionEntity> list = mongoTemplate.find(query, PositionEntity.class);

//        Query query = Query.query(Criteria.where("city").is("西安"));
//        List<PositionEntity> list = mongoTemplate.find(query, PositionEntity.class);
        return list;
    }
 }
