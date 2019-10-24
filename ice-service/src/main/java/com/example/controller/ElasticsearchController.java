package com.example.controller;

import com.example.entity.Qiancheng;
import com.example.entity.StatEntity;
import com.example.service.ElasticsearchService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.metrics.percentiles.Percentile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @author：张鸿建
 * @time：2019/10/17 15:59
 * @desc：
 **/
@Controller
@RequestMapping("qiancheng")
public class ElasticsearchController {

    @Autowired
    ElasticsearchService elasticsearchService;

    @GetMapping("/city/list")
    public List<Qiancheng> getListByCity(String city, int page, int size) {
        List<Qiancheng> list = elasticsearchService.getListByCity(city, page, size);
        return list;
    }
    @GetMapping("/stat/term")
    public Map<String, Integer> getStatByTerm(String city, String term) {
        Map<String, Integer> statByTerm = elasticsearchService.getStatByTerm(city, term);
        return statByTerm;
    }
    @GetMapping("/city/term")
    public StatEntity getStatPriceByTerm(String city, String term) {
        StatEntity statEntity = elasticsearchService.getStatPriceByTerm(city, term);
        return statEntity;
    }
    @GetMapping("/percen/price")
    public List<Percentile> getPercenRankByPrice(String city, double[] valus) {
        List<Percentile> percenRankByPrice = elasticsearchService.getPercenRankByPrice(city, valus);
        return percenRankByPrice;
    }


}
