package com.example.service;

import com.example.dao.QianchengDao;
import com.example.dao.QianchengRepository;
import com.example.entity.Qiancheng;
import com.example.entity.StatEntity;
import com.example.util.ElasticsearchUtil;
import com.google.common.collect.Lists;
import org.elasticsearch.ElasticsearchTimeoutException;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.percentiles.Percentile;
import org.elasticsearch.search.aggregations.metrics.percentiles.PercentileRanks;
import org.elasticsearch.search.aggregations.metrics.stats.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.*;

/**
 * @author：张鸿建
 * @time：2019/10/17 15:58
 * @desc：
 **/
@Service
public class ElasticsearchService {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    QianchengRepository qianchengRepository;


    public List<Qiancheng> getListByCity(String city, int page, int size) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(QueryBuilders.termQuery("city", city));
        PageRequest pageRequest = PageRequest.of(page, size);

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(boolQueryBuilder);
        nativeSearchQueryBuilder.withPageable(pageRequest);
        List<Qiancheng> qianchengs = elasticsearchTemplate.queryForList(nativeSearchQueryBuilder.build(), Qiancheng.class);
        return qianchengs;
    }

    public Map<String, Integer> getStatByTerm(String city, String term) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if (city != null) {
            boolQueryBuilder.must(QueryBuilders.termQuery("city", city));
        }
        Terms terms = ElasticsearchUtil.getAggTermsResult(boolQueryBuilder, null, term, null, 10, Qiancheng.class);
        List<? extends Terms.Bucket> buckets = terms.getBuckets();
        Map<String, Integer> hashMap = new HashMap();
        buckets.forEach(bucket -> {
            hashMap.put(bucket.getKeyAsString(), new Long(bucket.getDocCount()).intValue());
        });
        return hashMap;
    }

    public StatEntity getStatPriceByTerm(String city, String term) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if (city != null) {
            boolQueryBuilder.must(QueryBuilders.termQuery("city", city));
        }
        Stats stat = ElasticsearchUtil.getAggStatResult(boolQueryBuilder, null, term, Qiancheng.class);
        StatEntity statEntity = new StatEntity();
        statEntity.setAvg(stat.getAvg());
        statEntity.setCount(new Long(stat.getCount()).intValue());
        statEntity.setMax(stat.getMax());
        statEntity.setMin(stat.getMin());
        statEntity.setSum(stat.getSum());
        return statEntity;
    }

    public List<Percentile> getPercenRankByPrice(String city, double[] valus) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if (city != null) {
            boolQueryBuilder.must(QueryBuilders.termQuery("city", city));
        }
        PercentileRanks ranks = ElasticsearchUtil.getAggPercentilesResult(boolQueryBuilder, null, "price", valus, Qiancheng.class);
        Iterator<Percentile> iterator = ranks.iterator();
        List<Percentile> percentiles = Lists.newArrayList(iterator);
        return percentiles;
    }

}
