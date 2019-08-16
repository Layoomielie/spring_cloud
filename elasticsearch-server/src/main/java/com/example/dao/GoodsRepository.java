package com.example.dao;

import com.example.entity.GoodsInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author zhanghongjian
 * @Date 2019/4/25 14:04
 * @Description
 */

public interface  GoodsRepository  extends ElasticsearchRepository<GoodsInfo,Integer> {


}
