package com.example.dao;

import com.example.entity.Qiancheng;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author zhanghongjian
 * @Date 2019/4/25 14:04
 * @Description
 */

public interface QianchengRepository extends ElasticsearchRepository<Qiancheng,Integer> {


}
