package com.example.dao;


import com.example.entity.PositionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class MongoQueryDao {


    @Autowired
    MongoTemplate mongoTemplate;

    public int fintcount(){
        Query query = new Query(Criteria.where("city").is("西安"));
        long count = mongoTemplate.count(query, PositionEntity.class);
        return new Long(count).intValue();
    }


}
