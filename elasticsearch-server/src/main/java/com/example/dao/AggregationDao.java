package com.example.dao;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.InternalAggregation;
import org.elasticsearch.search.aggregations.ParsedAggregation;
import org.elasticsearch.search.aggregations.bucket.MultiBucketsAggregation;
import org.elasticsearch.search.aggregations.bucket.SingleBucketAggregation;
import org.elasticsearch.search.aggregations.metrics.NumericMetricsAggregation;
import org.elasticsearch.search.aggregations.metrics.scripted.ScriptedMetric;
import org.elasticsearch.search.sort.SortBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author：张鸿建
 * @time：2019/8/28 18:05
 * @desc：
 **/
@Repository
public class AggregationDao {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    public   <T> List<T> getAggSearchResult(BoolQueryBuilder queryBuilder, AggregationBuilder aggregationBuilder, Class<T> clazz) throws ExecutionException, InterruptedException {
        return getAggSearchResult(queryBuilder, null, aggregationBuilder, clazz);
    }


    public  <T> List<T> getAggSearchResult(QueryBuilder queryBuilder, SortBuilder sortBuilder, AggregationBuilder aggregationBuilder, Class<T> clazz) throws ExecutionException, InterruptedException {
        Document annotation = clazz.getAnnotation(Document.class);
        SearchRequestBuilder searchRequestBuilder = elasticsearchTemplate.getClient().prepareSearch(annotation.indexName()).setSize(0);
        if (queryBuilder != null) {
            searchRequestBuilder.setQuery(queryBuilder);
        }
        if (sortBuilder != null) {
            searchRequestBuilder.addSort(sortBuilder);
        }
        if (aggregationBuilder == null) {
            return null;
        } else {
            searchRequestBuilder.addAggregation(aggregationBuilder);
        }
        SearchResponse response = searchRequestBuilder.execute().get();
        Aggregation agg = response.getAggregations().get("agg");
        System.out.println("NumericMetricsAggregation: "+(agg instanceof NumericMetricsAggregation));
        System.out.println("SingleValue: "+(agg instanceof NumericMetricsAggregation.SingleValue));
        System.out.println("MultiValue: "+(agg instanceof NumericMetricsAggregation.MultiValue));
        System.out.println("---------------------------------------");
        System.out.println("SingleBucketAggregation: "+(agg instanceof SingleBucketAggregation));
        System.out.println("ScriptedMetric: "+(agg instanceof ScriptedMetric));
        System.out.println("MultiBucketsAggregation: "+(agg instanceof MultiBucketsAggregation));
        System.out.println("ParsedAggregation: "+(agg instanceof ParsedAggregation));
        System.out.println("InternalAggregation: "+(agg instanceof InternalAggregation));
        if(agg instanceof MultiBucketsAggregation){
            MultiBucketsAggregation terms = (MultiBucketsAggregation) agg;
            List<? extends MultiBucketsAggregation.Bucket> buckets = terms.getBuckets();
            System.out.println(buckets.size());
            buckets.forEach(bucket -> {
                String keyAsString = bucket.getKeyAsString();
                System.out.println("keyAsString: "+keyAsString);
                long docCount = bucket.getDocCount();
                System.out.println("docCount:"+docCount);
                Object key = bucket.getKey();
                System.out.println("key: "+key);
                Aggregation agg1 = bucket.getAggregations().get("subAgg");
                System.out.println(agg1==null);
            });
            return null;
        }else if(agg instanceof NumericMetricsAggregation.MultiValue) {
            NumericMetricsAggregation.MultiValue multiValue=(NumericMetricsAggregation.MultiValue) agg;

        }else {

        }
        return null;
    }
}
