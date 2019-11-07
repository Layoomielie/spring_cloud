package com.example.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author：张鸿建
 * @time：2019/10/17 15:59
 * @desc：
 **/
@RestController
public class ElasticsearchController {

    @Autowired
    ElasticsearchService elasticsearchService;

    /**
     * @param city
     * @param page
     * @param size
     * @Author: 张鸿建
     * @Date: 2019/11/7
     * @Desc: 表格统计
     */
    @GetMapping("/city/list")
    public List<Qiancheng> getListByCity(String city, int page, int size) {
        List<Qiancheng> list = elasticsearchService.getListByCity(city, page, size);
        list.forEach(qiancheng -> System.out.println(qiancheng.toString()));
        return list;
    }

    /**
     * @param city
     * @param term
     * @Author: 张鸿建
     * @Date: 2019/11/7
     * @Desc: 饼图统计 词云统计
     */
    @GetMapping("/city/term")
    public JSONArray getStatByTerm(String city, String term) {
        JSONArray statByTerm = elasticsearchService.getStatByTerm(city, term);
        return statByTerm;
    }

    /**
     * @param city
     * @param region
     * @param companyType
     * @param cotype
     * @param degree
     * @param workyear
     * @param companySize
     * @param jobTerm
     * @param term
     * @Author: 张鸿建
     * @Date: 2019/11/7
     * @Desc: 根据条件对加个参数统计
     */
    @GetMapping("/stat/term")
    public StatEntity getStatPriceByTerm(String city, String region, String companyType, String cotype, String degree, String workyear, String companySize, String jobTerm, String term) {
        StatEntity statEntity = elasticsearchService.getStatPriceByTerm(city, region, companyType, cotype, degree, workyear, companySize, jobTerm, term);
        return statEntity;
    }

    @GetMapping("/percen/price")
    public List<Percentile> getPercenRankByPrice(String city, String region, String companyType, String cotype, String degree, String workyear, String companySize, String jobTerm, String field, double[] valus) {
        List<Percentile> percenRankByPrice = elasticsearchService.getPercenRankByPrice(city, region, companyType, cotype, degree, workyear, companySize, jobTerm, field, valus);
        return percenRankByPrice;
    }

    /**
     * @param city
     * @param region
     * @param companyType
     * @param cotype
     * @param degree
     * @param workyear
     * @param companySize
     * @param jobTerm
     * @param field
     * @param queryType
     * @Author: 张鸿建
     * @Date: 2019/11/7
     * @Desc: 折线图统计 条形图统计
     */
    @GetMapping("/count/histogram")
    public JSONArray getCountByHistogram(String city, String region, String companyType, String cotype, String degree, String workyear, String companySize, String jobTerm, String field, String queryType) {
        JSONArray array = elasticsearchService.getCountByHistogram(city, region, companyType, cotype, degree, workyear, companySize, jobTerm, field, queryType);
        return array;
    }

    @GetMapping("/date/rank")
    public Map getDateRangeBucketByField(String city, String region, String companyType, String cotype, String degree, String workyear, String companySize, String jobTerm, int from, int to, String field) {
        Map<String, Integer> map = elasticsearchService.getDateRangeBucketByField(city, region, companyType, cotype, degree, workyear, companySize, jobTerm, from, to, field);
        return map;
    }

    /**
     * @param city
     * @param region
     * @param companyType
     * @param cotype
     * @param degree
     * @param workyear
     * @param companySize
     * @param jobTerm
     * @Author: 张鸿建
     * @Date: 2019/11/7
     * @Desc: 获取某个维度的count数量
     */
    @GetMapping("/query/count")
    public int getCountByQuery(String city, String region, String companyType, String cotype, String degree, String workyear, String companySize, String jobTerm) {
        int count = elasticsearchService.getCountByQuery(city, region, companyType, cotype, degree, workyear, companySize, jobTerm);
        return count;
    }

    /**
     * @param city
     * @param region
     * @param companyType
     * @param cotype
     * @param degree
     * @param workyear
     * @param companySize
     * @param jobTerm
     * @Author: 张鸿建
     * @Date: 2019/11/7
     * @Desc: 获取昨天统计的某个维度的数量
     */
    @GetMapping("/query/count/today")
    public int getTodayCount(String city, String region, String companyType, String cotype, String degree, String workyear, String companySize, String jobTerm) {
        int count = elasticsearchService.getTodayCount(city, region, companyType, cotype, degree, workyear, companySize, jobTerm);
        return count;
    }

    @GetMapping("/percentile/rank")
    public Map getPercentilesRank(String city, String region, String companyType, String cotype, String degree, String workyear, String companySize, String jobTerm, String field, double[] values) {
        Map map = elasticsearchService.getPercentilesRank(city, region, companyType, cotype, degree, workyear, companySize, jobTerm, field, values);
        return map;
    }

    /**
     * @param DateType
     * @param queryField
     * @param dateField
     * @Author: 张鸿建
     * @Date: 2019/11/7
     * @Desc: 折线图 条形图
     */
    @GetMapping("/all/field")
    public JSONArray getBrokenLine(String DateType, String queryField, String dateField) {
        JSONArray brokenLine = elasticsearchService.getBrokenLine(DateType, queryField, dateField);
        return brokenLine;
    }

    @GetMapping("/range/date/count")
    public JSONArray getRangeDateCount(String city, String region, String companyType, String cotype, String degree, String workyear, String companySize, String jobTerm, String dateField, String fromDate, String fromTo) {
        JSONArray rangeDateCount = elasticsearchService.getRangeDateCount(city, region, companyType, cotype, degree, workyear, companySize, jobTerm, dateField, fromDate, fromTo);
        return rangeDateCount;
    }

    @GetMapping("/missing/count")
    public int getMissFieldCount(String city, String region, String companyType, String cotype, String degree, String workyear, String companySize, String jobTerm, String queryField) {
        int missFieldCount = elasticsearchService.getMissFieldCount(city, region, companyType, cotype, degree, workyear, companySize, jobTerm, queryField);
        return missFieldCount;
    }
}
