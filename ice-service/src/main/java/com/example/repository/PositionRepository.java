package com.example.repository;

import com.example.entity.PositionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author：张鸿建
 * @time：2019/8/19 14:01
 * @desc：
 **/
@Repository
public interface  PositionRepository extends MongoRepository<PositionEntity,String> {



}
