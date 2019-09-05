package com.example.utils;

import com.example.exception.NotifyException;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.*;
import org.elasticsearch.search.aggregations.bucket.MultiBucketsAggregation;
import org.elasticsearch.search.aggregations.bucket.SingleBucketAggregation;
import org.elasticsearch.search.aggregations.bucket.filter.Filter;
import org.elasticsearch.search.aggregations.bucket.filter.FilterAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.filter.Filters;
import org.elasticsearch.search.aggregations.bucket.filter.FiltersAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.range.DateRangeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.aggregations.bucket.range.RangeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.NumericMetricsAggregation;
import org.elasticsearch.search.aggregations.metrics.avg.Avg;
import org.elasticsearch.search.aggregations.metrics.cardinality.Cardinality;
import org.elasticsearch.search.aggregations.metrics.cardinality.CardinalityAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.max.Max;
import org.elasticsearch.search.aggregations.metrics.max.MaxAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.min.Min;
import org.elasticsearch.search.aggregations.metrics.min.MinAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.percentiles.PercentileRanks;
import org.elasticsearch.search.aggregations.metrics.percentiles.PercentileRanksAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.scripted.ScriptedMetric;
import org.elasticsearch.search.aggregations.metrics.stats.Stats;
import org.elasticsearch.search.aggregations.metrics.stats.StatsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
*
* @Author: 张鸿建
* @Date: 2019/9/5
* @Desc: 
*/
@Component
public class ElasticsearchUtil {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    private static ElasticsearchUtil elasticsearchUtil;

    private ElasticsearchUtil() {
    }

    @PostConstruct
    public void init() {
        elasticsearchUtil = this;
        elasticsearchUtil.elasticsearchTemplate = this.elasticsearchTemplate;
    }

    public static <T> List<T> getAggSearchResult(BoolQueryBuilder queryBuilder, AggregationBuilder aggregationBuilder, Class<T> clazz) throws ExecutionException, InterruptedException {
        return getAggSearchResult(queryBuilder, null, aggregationBuilder, clazz);
    }


    public static <T> List<T> getAggSearchResult(QueryBuilder queryBuilder, SortBuilder sortBuilder, AggregationBuilder aggregationBuilder, Class<T> clazz) throws ExecutionException, InterruptedException {
        Document annotation = clazz.getAnnotation(Document.class);
        SearchRequestBuilder searchRequestBuilder = elasticsearchUtil.elasticsearchTemplate.getClient().prepareSearch(annotation.indexName()).setSize(0);
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
        System.out.println("NumericMetricsAggregation: " + (agg instanceof NumericMetricsAggregation));
        System.out.println("SingleValue: " + (agg instanceof NumericMetricsAggregation.SingleValue));
        System.out.println("MultiValue: " + (agg instanceof NumericMetricsAggregation.MultiValue));
        System.out.println("---------------------------------------");
        System.out.println("SingleBucketAggregation: " + (agg instanceof SingleBucketAggregation));
        System.out.println("ScriptedMetric: " + (agg instanceof ScriptedMetric));
        System.out.println("MultiBucketsAggregation: " + (agg instanceof MultiBucketsAggregation));
        System.out.println("ParsedAggregation: " + (agg instanceof ParsedAggregation));
        System.out.println("InternalAggregation: " + (agg instanceof InternalAggregation));
        if (agg instanceof MultiBucketsAggregation) {
            MultiBucketsAggregation terms = (MultiBucketsAggregation) agg;
            List<? extends MultiBucketsAggregation.Bucket> buckets = terms.getBuckets();
            System.out.println(buckets.size());
            buckets.forEach(bucket -> {
                String keyAsString = bucket.getKeyAsString();
                System.out.println("keyAsString: " + keyAsString);
                long docCount = bucket.getDocCount();
                System.out.println("docCount:" + docCount);
                Object key = bucket.getKey();
                System.out.println("key: " + key);
                Aggregation agg1 = bucket.getAggregations().get("subAgg");
                System.out.println(agg1 == null);
            });
            return null;
        } else if (agg instanceof NumericMetricsAggregation.MultiValue) {
            NumericMetricsAggregation.MultiValue multiValue = (NumericMetricsAggregation.MultiValue) agg;

        } else {

        }
        return null;
    }


    public static <T> Range getAggDateHistogramResult(QueryBuilder queryBuilder, SortBuilder sortBuilder, DateHistogramAggregationBuilder dateAggregationBuilder, Class<T> clazz) {
        SearchRequestBuilder searchRequestsBuilder = getSearchRequestsBuilder(queryBuilder, sortBuilder, clazz);
        Aggregation aggregation = searchRequestsBuilder.setSize(0).addAggregation(dateAggregationBuilder).execute().actionGet().getAggregations().get("agg");
        return getRange(aggregation);
    }

    public static <T> Range getAggDateResult(QueryBuilder queryBuilder, SortBuilder sortBuilder, DateRangeAggregationBuilder dateAggregationBuilder, Class<T> clazz) {
        SearchRequestBuilder searchRequestsBuilder = getSearchRequestsBuilder(queryBuilder, sortBuilder, clazz);
        Aggregation aggregation = searchRequestsBuilder.setSize(0).addAggregation(dateAggregationBuilder).execute().actionGet().getAggregations().get("agg");
        return getRange(aggregation);
    }

    private static Range getRange(Aggregation aggregation) {
        if (aggregation instanceof Range) {
            Range range = (Range) aggregation;
            return range;
        } else {
            throw new NotifyException("getAggFilterResult error");
        }
    }

    public static <T> Range getAggRangeResult(QueryBuilder queryBuilder, SortBuilder sortBuilder, RangeAggregationBuilder rangeAggregationBuilder, Class<T> clazz) {
        SearchRequestBuilder searchRequestsBuilder = getSearchRequestsBuilder(queryBuilder, sortBuilder, clazz);
        Aggregation aggregation = searchRequestsBuilder.setSize(0).addAggregation(rangeAggregationBuilder).execute().actionGet().getAggregations().get("agg");
        return getRange(aggregation);
    }

    public static <T> Filter getAggFilterResult(QueryBuilder queryBuilder, SortBuilder sortBuilder, Class<T> clazz) {
        SearchRequestBuilder searchRequestsBuilder = getSearchRequestsBuilder(queryBuilder, sortBuilder, clazz);
        FilterAggregationBuilder aggregationBuilder = AggregationBuilders.filter("agg", queryBuilder);
        Aggregation aggregation = searchRequestsBuilder.setSize(0).addAggregation(aggregationBuilder).execute().actionGet().getAggregations().get("agg");

        if (aggregation instanceof Filter) {
            Filter filter = (Filter) aggregation;
            return filter;
        } else {
            throw new NotifyException("getAggFilterResult error");
        }
    }

    public static <T> Filters getAggFiltersResult(QueryBuilder queryBuilder, SortBuilder sortBuilder, QueryBuilder[] queryBuilders, Class<T> clazz) {
        SearchRequestBuilder searchRequestsBuilder = getSearchRequestsBuilder(queryBuilder, sortBuilder, clazz);
        FiltersAggregationBuilder aggregationBuilder = AggregationBuilders.filters("agg", queryBuilders).otherBucketKey("other");
        Aggregation aggregation = searchRequestsBuilder.setSize(0).addAggregation(aggregationBuilder).execute().actionGet().getAggregations().get("agg");
        if (aggregation instanceof Filters) {
            Filters filters = (Filters) aggregation;
            return filters;
        } else {
            throw new NotifyException("getAggFilterResult error");
        }
    }

    public static <T> Terms getAggTermsResult(QueryBuilder queryBuilder, SortBuilder sortBuilder, String field, BucketOrder bucketOrder, Integer size, Class<T> clazz) {
        SearchRequestBuilder searchRequestsBuilder = getSearchRequestsBuilder(queryBuilder, sortBuilder, clazz);
        TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms("agg").field(field);
        if (null != size && size > 0) {
            aggregationBuilder.size(size);
        } else {
            aggregationBuilder.size(10);
        }
        if (bucketOrder != null) {
            aggregationBuilder.order(bucketOrder);
        }
        // 根据脚本计算分值
        //aggregationBuilder.script();
        //筛选分组
        //aggregationBuilder.executionHint();
        //aggregationBuilder.includeExclude();
        Aggregation aggregation = searchRequestsBuilder.setSize(0).addAggregation(aggregationBuilder).execute().actionGet().getAggregations().get("agg");
        if (aggregation instanceof Terms) {
            Terms terms = (Terms) aggregation;
            return terms;
        } else {
            throw new NotifyException("getAggTermsResult error");
        }
    }

    public static <T> PercentileRanks getAggPercentilesResult(QueryBuilder queryBuilder, SortBuilder sortBuilder, String field, double[] values, Class<T> clazz) {
        SearchRequestBuilder searchRequestsBuilder = getSearchRequestsBuilder(queryBuilder, sortBuilder, clazz);
        PercentileRanksAggregationBuilder aggregationBuilder = AggregationBuilders.percentileRanks("agg", values).field(field);
        Aggregation aggregation = searchRequestsBuilder.setSize(0).addAggregation(aggregationBuilder).execute().actionGet().getAggregations().get("agg");
        if (aggregation instanceof PercentileRanks) {
            PercentileRanks percentileRanks = (PercentileRanks) aggregation;
            return percentileRanks;
        } else {
            throw new NotifyException("getAggPercentilesResult error");
        }
    }

    public static <T> Cardinality getAggCardinalityResult(QueryBuilder queryBuilder, SortBuilder sortBuilder, String field, Class<T> clazz) {
        SearchRequestBuilder searchRequestsBuilder = getSearchRequestsBuilder(queryBuilder, sortBuilder, clazz);
        CardinalityAggregationBuilder aggregationBuilder = AggregationBuilders.cardinality("agg").field(field);
        Aggregation aggregation = searchRequestsBuilder.setSize(0).addAggregation(aggregationBuilder).execute().actionGet().getAggregations().get("agg");
        if (aggregation instanceof Cardinality) {
            Cardinality cardinality = (Cardinality) aggregation;
            return cardinality;
        } else {
            throw new NotifyException("getAggCardinalityResult error");
        }
    }


    public static <T> Avg getAggAvgResult(QueryBuilder queryBuilder, SortBuilder sortBuilder, String field, Class<T> clazz) {
        SearchRequestBuilder searchRequestsBuilder = getSearchRequestsBuilder(queryBuilder, sortBuilder, clazz);
        MaxAggregationBuilder aggregationBuilder = AggregationBuilders.max("agg").field(field);
        Aggregation aggregation = searchRequestsBuilder.setSize(0).addAggregation(aggregationBuilder).execute().actionGet().getAggregations().get("agg");
        if (aggregation instanceof Avg) {
            Avg avg = (Avg) aggregation;
            return avg;
        } else {
            throw new NotifyException("getAggAvgResult error");
        }
    }

    public static <T> Sum getAggSumResult(QueryBuilder queryBuilder, SortBuilder sortBuilder, String field, Class<T> clazz) {
        SearchRequestBuilder searchRequestsBuilder = getSearchRequestsBuilder(queryBuilder, sortBuilder, clazz);
        SumAggregationBuilder aggregationBuilder = AggregationBuilders.sum("agg").field("field");
        Aggregation agg = searchRequestsBuilder.setSize(0).addAggregation(aggregationBuilder).execute().actionGet().getAggregations().get("agg");
        if (agg instanceof Sum) {
            Sum sum = (Sum) agg;
            return sum;
        } else {
            throw new NotifyException("getAggSumResult error");
        }
    }

    public static <T> Max getAggMaxResult(QueryBuilder queryBuilder, SortBuilder sortBuilder, String field, Class<T> clazz) {
        SearchRequestBuilder searchRequestsBuilder = getSearchRequestsBuilder(queryBuilder, sortBuilder, clazz);
        MaxAggregationBuilder aggregationBuilder = AggregationBuilders.max("agg").field(field);
        Aggregation aggregation = searchRequestsBuilder.setSize(0).addAggregation(aggregationBuilder).execute().actionGet().getAggregations().get("agg");
        if (aggregation instanceof Max) {
            Max max = (Max) aggregation;
            return max;
        } else {
            throw new NotifyException("getAggMaxResult error");
        }
    }

    public static <T> Min getAggMinResult(QueryBuilder queryBuilder, SortBuilder sortBuilder, String field, Class<T> clazz) {
        SearchRequestBuilder searchRequestsBuilder = getSearchRequestsBuilder(queryBuilder, sortBuilder, clazz);
        MinAggregationBuilder aggregationBuilder = AggregationBuilders.min("agg").field(field);
        Aggregation aggregation = searchRequestsBuilder.setSize(0).addAggregation(aggregationBuilder).execute().actionGet().getAggregations().get("agg");
        if (aggregation instanceof Min) {
            Min min = (Min) aggregation;
            return min;
        } else {
            throw new NotifyException("getAggMinResult error");
        }
    }

    public static <T> Stats getAggStatResult(QueryBuilder queryBuilder, SortBuilder sortBuilder, String field, Class<T> clazz) {
        SearchRequestBuilder searchRequestsBuilder = getSearchRequestsBuilder(queryBuilder, sortBuilder, clazz);
        StatsAggregationBuilder aggregationBuilder = AggregationBuilders.stats("agg").field(field);
        Aggregations aggregations = searchRequestsBuilder.setSize(0).addAggregation(aggregationBuilder).execute().actionGet().getAggregations().get("agg");
        if (aggregations instanceof Stats) {
            Stats stats = (Stats) aggregations;
            return stats;
        } else {
            throw new NotifyException("getAggStatResult error");
        }
    }

    private static <T> SearchRequestBuilder getSearchRequestsBuilder(QueryBuilder queryBuilder, SortBuilder sortBuilder, Class<T> clazz) {
        Document annotation = clazz.getAnnotation(Document.class);
        SearchRequestBuilder searchRequestBuilder = elasticsearchUtil.elasticsearchTemplate.getClient().prepareSearch(annotation.indexName()).setSize(0);
        if (queryBuilder != null) {
            searchRequestBuilder = searchRequestBuilder.setQuery(queryBuilder);
        }
        if (sortBuilder != null) {
            searchRequestBuilder = searchRequestBuilder.addSort(sortBuilder);
        }
        return searchRequestBuilder;
    }

}
