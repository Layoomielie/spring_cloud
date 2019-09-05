package com.example.service;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author：张鸿建
 * @time：2019/9/2 10:23
 * @desc：
 **/
@Service
public class ClassAnalyseService {


    // 统计总时长
    public List getStatAggResult() throws ExecutionException, InterruptedException {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        // 获取去重classId 根据班级统计

        //   TermsAggregationBuilder agg = AggregationBuilders.terms("agg").field("classId");

        //MultiValue
        //  StatsAggregationBuilder agg = AggregationBuilders.stats("agg").field("chapterNum");

        //SingleValue
        //   MaxAggregationBuilder agg = AggregationBuilders.max("agg").field("chapterNum");

        //SingleValue
        //CardinalityAggregationBuilder agg = AggregationBuilers.cardinality("agg").field("chapterNum");

        //MultiValue
        //ValueCountAggregationBuilder agg = AggregationBuilders.count("agg").field("contentType");

        //MultiValue
        //ExtendedStatsAggregationBuilder agg = AggregationBuilders.extendedStats("agg").field("chapterNum");

        //MultiValue
        //PercentilesAggregationBuilder agg = AggregationBuilders.percentiles("agg").field("chapterNum");

        //SingleBucketAggregation
        //  TermQueryBuilder queryBuilder = QueryBuilders.termQuery("contentType", 0);
        //  FilterAggregationBuilder agg = AggregationBuilders.filter("agg", queryBuilder);

        // MultiValue
        //RangeAggregationBuilder agg = AggregationBuilders.range("agg").field("chapterNum").addRange("A",1, 20).addRange("B",21, 40);

        //MultiBucketsAggregation
        //DateRangeAggregationBuilder agg = AggregationBuilders.dateRange("agg").field("updateDate").addRange("2019-01-01", "now").format("yyyy-MM-dd");

        //MultiBucketsAggregation
        //DateHistogramAggregationBuilder agg = AggregationBuilders.dateHistogram("agg").field("updateDate").dateHistogramInterval(DateHistogramInterval.MONTH);
        //List<AggResultDto> aggSearchResult = ElasticsearchUtil.getAggSearchResult(boolQueryBuilder, agg, ChapterPublish.class);


        return null;

    }
}
