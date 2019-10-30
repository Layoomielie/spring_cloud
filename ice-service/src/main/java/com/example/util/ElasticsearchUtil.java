package com.example.util;

import com.example.exception.NotifyException;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.reindex.*;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.MultiBucketsAggregation;
import org.elasticsearch.search.aggregations.bucket.filter.Filter;
import org.elasticsearch.search.aggregations.bucket.filter.FilterAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.filter.FiltersAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.range.DateRangeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.aggregations.bucket.range.RangeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.NumericMetricsAggregation;
import org.elasticsearch.search.aggregations.metrics.avg.AvgAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.cardinality.Cardinality;
import org.elasticsearch.search.aggregations.metrics.cardinality.CardinalityAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.max.MaxAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.min.Min;
import org.elasticsearch.search.aggregations.metrics.min.MinAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.percentiles.PercentileRanks;
import org.elasticsearch.search.aggregations.metrics.percentiles.PercentileRanksAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.stats.Stats;
import org.elasticsearch.search.aggregations.metrics.stats.StatsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.valuecount.ValueCountAggregationBuilder;
import org.elasticsearch.search.collapse.CollapseBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
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

    /**
     * @param queryBuilder
     * @param sortBuilder
     * @param field
     * @param clazz
     * @Author: 张鸿建
     * @Date: 2019/9/5
     * @Desc: 折叠查询
     */
    public static <T> List<String> getCollapse(QueryBuilder queryBuilder, SortBuilder sortBuilder, String field, Integer size, Class<T> clazz) throws ExecutionException, InterruptedException {
        Document annotation = clazz.getAnnotation(Document.class);
        Client client = elasticsearchUtil.elasticsearchTemplate.getClient();
        CollapseBuilder collapseBuilder = new CollapseBuilder(field);
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(annotation.indexName()).setCollapse(collapseBuilder);
        if (queryBuilder != null) {
            searchRequestBuilder.setQuery(queryBuilder);
        }
        if (sortBuilder != null) {
            searchRequestBuilder.addSort(sortBuilder);
        }
        if (null != size && size > 0) {
            searchRequestBuilder.setSize(size);
        } else {
            searchRequestBuilder.setSize(10);
        }
        SearchHit[] hits = searchRequestBuilder.execute().get().getHits().getHits();
        ArrayList<String> list = new ArrayList<>();
        for (SearchHit hit : hits) {
            list.add(hit.field(field).getValue());
        }
        return list;
    }

    /**
     * @param entitys
     * @param size
     * @param clazz
     * @Author: 张鸿建
     * @Date: 2019/9/26
     * @Desc:
     */
    public static <T> Boolean bucketInsert(List<T> entitys, Integer size, Class<T> clazz) {
        Document annotation = clazz.getAnnotation(Document.class);
        Client client = elasticsearchUtil.elasticsearchTemplate.getClient();
        BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();
        entitys.forEach(baseEntity -> {
            IndexRequestBuilder indexRequestBuilder = client.prepareIndex(annotation.indexName(), annotation.type(), "id").setSource(baseEntity);
            bulkRequestBuilder.add(indexRequestBuilder);
        });
        BulkResponse bulkResponse = bulkRequestBuilder.get();
        RestStatus status = bulkResponse.status();
        elasticsearchUtil.elasticsearchTemplate.refresh(annotation.indexName());
        if (bulkResponse.hasFailures()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @param queryBuilder
     * @param size
     * @param clazz
     * @Author: 张鸿建
     * @Date: 2019/9/26
     * @Desc: 根据条件删除
     */
    public static <T> int deleteByQuery(QueryBuilder queryBuilder, Integer size, Class<T> clazz) {
        Document annotation = clazz.getAnnotation(Document.class);
        Client client = elasticsearchUtil.elasticsearchTemplate.getClient();
        DeleteByQueryRequestBuilder filter = DeleteByQueryAction.INSTANCE.newRequestBuilder(client).filter(queryBuilder);
        if (size != null && size > 1) {
            filter.size(size);
        }
        BulkByScrollResponse response = filter.source(annotation.indexName()).get();
        long count = response.getDeleted();
        return new Long(count).intValue();
    }

    /**
     * @param queryBuilder
     * @param field
     * @param value
     * @param size
     * @param clazz
     * @Author: 张鸿建
     * @Date: 2019/9/26
     * @Desc: 根据条件进行修改
     */
    public static <T> int updateByQuery(QueryBuilder queryBuilder, String field, String value, Integer size, Class<T> clazz) {
        Document annotation = clazz.getAnnotation(Document.class);
        Client client = elasticsearchUtil.elasticsearchTemplate.getClient();
        Script script = new Script(ScriptType.INLINE, "painless", "ctx._source['" + field + "']='" + value + "'", Collections.emptyMap());
        UpdateByQueryRequestBuilder builder = UpdateByQueryAction.INSTANCE.newRequestBuilder(client).script(script);
        if (size != null && size > 0) {
            builder.size(size);
        }
        BulkByScrollResponse response = builder
                .filter(queryBuilder)
                .source(annotation.indexName())
                .get();
        long updated = response.getUpdated();
        return new Long(updated).intValue();
    }


    public static <T> MultiBucketsAggregation getSubAggDateHistogramResult(DateHistogramAggregationBuilder dateAggregationBuilder, TermsAggregationBuilder termsAggregationBuilder, Class<T> clazz) {
        SearchRequestBuilder searchRequestsBuilder = getSearchRequestsBuilder(null, null, clazz);
        DateHistogramAggregationBuilder dateHistogramAggregationBuilder = dateAggregationBuilder.subAggregation(termsAggregationBuilder);
        Aggregation aggregation = searchRequestsBuilder.setSize(0).addAggregation(dateHistogramAggregationBuilder).execute().actionGet().getAggregations().get("agg");
        if (aggregation instanceof MultiBucketsAggregation) {
            MultiBucketsAggregation range = (MultiBucketsAggregation) aggregation;
            return range;
        } else {
            throw new NotifyException("getAggFilterResult error");
        }
    }

    /**
     * @param queryBuilder
     * @param sortBuilder
     * @param dateAggregationBuilder
     * @param clazz
     * @Author: 张鸿建
     * @Date: 2019/9/5
     * @Desc:
     */
    public static <T> MultiBucketsAggregation getAggDateHistogramResult(QueryBuilder queryBuilder, SortBuilder sortBuilder, DateHistogramAggregationBuilder dateAggregationBuilder, Class<T> clazz) {
        SearchRequestBuilder searchRequestsBuilder = getSearchRequestsBuilder(queryBuilder, sortBuilder, clazz);
        Aggregation aggregation = searchRequestsBuilder.setSize(0).addAggregation(dateAggregationBuilder).execute().actionGet().getAggregations().get("agg");

        if (aggregation instanceof MultiBucketsAggregation) {
            MultiBucketsAggregation range = (MultiBucketsAggregation) aggregation;
            return range;
        } else {
            throw new NotifyException("getAggFilterResult error");
        }
    }

    /**
     * @param queryBuilder
     * @param sortBuilder
     * @param dateAggregationBuilder
     * @param clazz
     * @Author: 张鸿建
     * @Date: 2019/9/5
     * @Desc:
     */
    public static <T> Range getAggDateResult(QueryBuilder queryBuilder, SortBuilder sortBuilder, DateRangeAggregationBuilder dateAggregationBuilder, Class<T> clazz) {
        SearchRequestBuilder searchRequestsBuilder = getSearchRequestsBuilder(queryBuilder, sortBuilder, clazz);
        Aggregation aggregation = searchRequestsBuilder.setSize(0).addAggregation(dateAggregationBuilder).execute().actionGet().getAggregations().get("agg");
        if (aggregation instanceof Range) {
            Range range = (Range) aggregation;
            return range;
        } else {
            throw new NotifyException("getAggFilterResult error");
        }
    }

    /**
     * @param queryBuilder
     * @param sortBuilder
     * @param rangeAggregationBuilder
     * @param clazz
     * @Author: 张鸿建
     * @Date: 2019/9/5
     * @Desc:
     */
    public static <T> Range getAggRangeResult(QueryBuilder queryBuilder, SortBuilder sortBuilder, RangeAggregationBuilder rangeAggregationBuilder, Class<T> clazz) {
        SearchRequestBuilder searchRequestsBuilder = getSearchRequestsBuilder(queryBuilder, sortBuilder, clazz);
        Aggregation aggregation = searchRequestsBuilder.setSize(0).addAggregation(rangeAggregationBuilder).execute().actionGet().getAggregations().get("agg");

        if (aggregation instanceof Range) {
            Range range = (Range) aggregation;
            return range;
        } else {
            throw new NotifyException("getAggFilterResult error");
        }
    }

    /**
     * @param queryBuilder
     * @param sortBuilder
     * @param clazz
     * @Author: 张鸿建
     * @Date: 2019/9/5
     * @Desc:
     */
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

    /**
     * @param queryBuilder
     * @param sortBuilder
     * @param queryBuilders
     * @param clazz
     * @Author: 张鸿建
     * @Date: 2019/9/5
     * @Desc:
     */
    public static <T> Filter getAggFiltersResult(QueryBuilder queryBuilder, SortBuilder sortBuilder, QueryBuilder[] queryBuilders, Class<T> clazz) {
        SearchRequestBuilder searchRequestsBuilder = getSearchRequestsBuilder(queryBuilder, sortBuilder, clazz);
        FiltersAggregationBuilder aggregationBuilder = AggregationBuilders.filters("agg", queryBuilders).otherBucketKey("other");
        Aggregation aggregation = searchRequestsBuilder.setSize(0).addAggregation(aggregationBuilder).execute().actionGet().getAggregations().get("agg");
        if (aggregation instanceof Filter) {
            Filter filter = (Filter) aggregation;
            return filter;
        } else {
            throw new NotifyException("getAggFilterResult error");
        }
    }

    /**
     * @param queryBuilder
     * @param sortBuilder
     * @param field
     * @param bucketOrder
     * @param size
     * @param clazz
     * @Author: 张鸿建
     * @Date: 2019/9/5
     * @Desc:
     */
    public static <T> Terms getAggTermsResult(QueryBuilder queryBuilder, SortBuilder sortBuilder, String field, BucketOrder bucketOrder, Integer size, Class<T> clazz) {
        SearchRequestBuilder searchRequestsBuilder = getSearchRequestsBuilder(queryBuilder, sortBuilder, clazz);
        TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms("agg").field(field).order(BucketOrder.count(false));

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

    /**
     * @param queryBuilder
     * @param sortBuilder
     * @param field
     * @param values
     * @param clazz
     * @Author: 张鸿建
     * @Date: 2019/9/5
     * @Desc:
     */
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

    /**
     * @param queryBuilder
     * @param sortBuilder
     * @param field
     * @param clazz
     * @Author: 张鸿建
     * @Date: 2019/9/5
     * @Desc:
     */
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

    /**
     * @param queryBuilder
     * @param sortBuilder
     * @param field
     * @param clazz
     * @Author: 张鸿建
     * @Date: 2019/9/5
     * @Desc:
     */
    public static <T> NumericMetricsAggregation.SingleValue getAggAvgResult(QueryBuilder queryBuilder, SortBuilder sortBuilder, String field, Class<T> clazz) {
        SearchRequestBuilder searchRequestsBuilder = getSearchRequestsBuilder(queryBuilder, sortBuilder, clazz);
        AvgAggregationBuilder aggregationBuilder = AggregationBuilders.avg("agg").field(field);
        Aggregation aggregation = searchRequestsBuilder.setSize(0).addAggregation(aggregationBuilder).execute().actionGet().getAggregations().get("agg");
        if (aggregation instanceof NumericMetricsAggregation.SingleValue) {
            NumericMetricsAggregation.SingleValue avg = (NumericMetricsAggregation.SingleValue) aggregation;
            return avg;
        } else {
            throw new NotifyException("getAggAvgResult error");
        }
    }

    /**
     * @param queryBuilder
     * @param sortBuilder
     * @param field
     * @param clazz
     * @Author: 张鸿建
     * @Date: 2019/9/5
     * @Desc:
     */
    public static <T> NumericMetricsAggregation.SingleValue getAggSumResult(QueryBuilder queryBuilder, SortBuilder sortBuilder, String field, Class<T> clazz) {
        SearchRequestBuilder searchRequestsBuilder = getSearchRequestsBuilder(queryBuilder, sortBuilder, clazz);
        SumAggregationBuilder aggregationBuilder = AggregationBuilders.sum("agg").field(field);
        Aggregation agg = searchRequestsBuilder.setSize(0).addAggregation(aggregationBuilder).execute().actionGet().getAggregations().get("agg");
        if (agg instanceof NumericMetricsAggregation.SingleValue) {
            NumericMetricsAggregation.SingleValue singleValue = (NumericMetricsAggregation.SingleValue) agg;
            return singleValue;
        } else {
            throw new NotifyException("getAggSumResult error");
        }
    }

    /**
     * @param queryBuilder
     * @param sortBuilder
     * @param field
     * @param clazz
     * @Author: 张鸿建
     * @Date: 2019/9/5
     * @Desc:
     */
    public static <T> NumericMetricsAggregation.SingleValue getAggMaxResult(QueryBuilder queryBuilder, SortBuilder sortBuilder, String field, Class<T> clazz) {
        SearchRequestBuilder searchRequestsBuilder = getSearchRequestsBuilder(queryBuilder, sortBuilder, clazz);
        MaxAggregationBuilder aggregationBuilder = AggregationBuilders.max("agg").field(field);
        Aggregation aggregation = searchRequestsBuilder.setSize(0).addAggregation(aggregationBuilder).execute().actionGet().getAggregations().get("agg");
        if (aggregation instanceof NumericMetricsAggregation.SingleValue) {
            NumericMetricsAggregation.SingleValue singleValue = (NumericMetricsAggregation.SingleValue) aggregation;
            return singleValue;
        } else {
            throw new NotifyException("getAggMaxResult error");
        }
    }

    /**
     * @param queryBuilder
     * @param sortBuilder
     * @param field
     * @param clazz
     * @Author: 张鸿建
     * @Date: 2019/9/24
     * @Desc:
     */
    public static <T> NumericMetricsAggregation.SingleValue getAggCountResult(QueryBuilder queryBuilder, SortBuilder sortBuilder, String field, Class<T> clazz) {
        SearchRequestBuilder searchRequestsBuilder = getSearchRequestsBuilder(queryBuilder, sortBuilder, clazz);
        ValueCountAggregationBuilder aggregationBuilder = AggregationBuilders.count("agg").field(field);
        Aggregation aggregation = searchRequestsBuilder.setSize(0).addAggregation(aggregationBuilder).execute().actionGet().getAggregations().get("agg");
        if (aggregation instanceof NumericMetricsAggregation.SingleValue) {
            NumericMetricsAggregation.SingleValue singleValue = (NumericMetricsAggregation.SingleValue) aggregation;
            return singleValue;
        } else {
            throw new NotifyException("getAggMaxResult error");
        }
    }

    /**
     * @param queryBuilder
     * @param sortBuilder
     * @param field
     * @param clazz
     * @Author: 张鸿建
     * @Date: 2019/9/5
     * @Desc:
     */
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

    /**
     * @param queryBuilder
     * @param sortBuilder
     * @param field
     * @param clazz
     * @Author: 张鸿建
     * @Date: 2019/9/5
     * @Desc:
     */
    public static <T> Stats getAggStatResult(QueryBuilder queryBuilder, SortBuilder sortBuilder, String field, Class<T> clazz) {
        SearchRequestBuilder searchRequestsBuilder = getSearchRequestsBuilder(queryBuilder, sortBuilder, clazz);
        StatsAggregationBuilder aggregationBuilder = AggregationBuilders.stats("agg").field(field);
        SearchResponse response = searchRequestsBuilder.setSize(0).addAggregation(aggregationBuilder).execute().actionGet();
        Aggregation aggregation = response.getAggregations().getAsMap().get("agg");
        if (aggregation instanceof Stats) {
            Stats stats = (Stats) aggregation;
            return stats;
        } else {
            throw new NotifyException("getAggStatResult error");
        }
    }


    /**
     * @param queryBuilder
     * @param sortBuilder
     * @param clazz
     * @Author: 张鸿建
     * @Date: 2019/9/5
     * @Desc:
     */
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
