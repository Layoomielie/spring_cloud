package com.example.controller;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhanghongjian
 * @Date 2019/4/28 14:30
 * @Description
 */
@RestController
@RequestMapping("/template")
public class GoodsTemplateController {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @RequestMapping("/all")
    public List<Map<String,Object>> searchAll() throws Exception{
        Client client = elasticsearchTemplate.getClient();

        SearchRequestBuilder srb = client.prepareSearch("goods").setTypes("good");
        SearchResponse sr = srb.setQuery(QueryBuilders.matchAllQuery()).execute().actionGet();
        SearchHits hits = sr.getHits();
        List<Map<String, Object>> list = new ArrayList<>();
        for (SearchHit hit:hits
             ) {
            Map<String, Object> map = hit.getSourceAsMap();
            list.add(map);
            System.out.println(hit.getSourceAsString());
        }
        return list;
    }
}
