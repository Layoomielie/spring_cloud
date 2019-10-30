package com.example.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.dao.QianchengDao;
import com.example.dao.QianchengRepository;
import com.example.entity.Qiancheng;
import com.example.entity.StatEntity;
import com.example.exception.NotifyException;
import com.example.util.DateStringUtil;
import com.example.util.ElasticsearchUtil;
import com.google.common.collect.Lists;
import org.elasticsearch.ElasticsearchTimeoutException;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.MultiBucketsAggregation;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.range.DateRangeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.aggregations.bucket.range.RangeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.percentiles.Percentile;
import org.elasticsearch.search.aggregations.metrics.percentiles.PercentileRanks;
import org.elasticsearch.search.aggregations.metrics.percentiles.PercentileRanksAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.stats.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
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

    /**
     * @Author: 张鸿建
     * @Date: 2019/10/24
     * @Desc: 通过city返回list
     */
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

    /**
     * @param city
     * @param term
     * @Author: 张鸿建
     * @Date: 2019/10/24
     * @Desc:
     */
    public JSONObject getStatByTerm(String city, String term) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if (city != null) {
            boolQueryBuilder.must(QueryBuilders.termQuery("city", city));
        }
        Terms terms = ElasticsearchUtil.getAggTermsResult(boolQueryBuilder, null, term, null, 10, Qiancheng.class);
        List<? extends Terms.Bucket> buckets = terms.getBuckets();
        JSONObject jsonObject = new JSONObject();
        Map<String, Integer> hashMap = new HashMap();
        buckets.forEach(bucket -> {
            jsonObject.put(bucket.getKeyAsString(), new Long(bucket.getDocCount()).intValue());
        });
        return jsonObject;
    }

    /**
     * @param city
     * @param term
     * @Author: 张鸿建
     * @Date: 2019/10/24
     * @Desc:
     */
    public StatEntity getStatPriceByTerm(String city, String region, String companyType, String cotype, String degree, String workyear, String companySize, String jobTerm, String term) {
       BoolQueryBuilder boolQueryBuilder = getNativeBuilder(city, region, companyType, cotype, degree, workyear, companySize, jobTerm);
        Stats stat = ElasticsearchUtil.getAggStatResult(boolQueryBuilder, null, term, Qiancheng.class);
        StatEntity statEntity = new StatEntity();
        statEntity.setAvg(stat.getAvg());
        statEntity.setCount(new Long(stat.getCount()).intValue());
        statEntity.setMax(stat.getMax());
        statEntity.setMin(stat.getMin());
        statEntity.setSum(stat.getSum());
        return statEntity;
    }

    /**
     * @param city
     * @param valus
     * @Author: 张鸿建
     * @Date: 2019/10/25
     * @Desc: 获取比当前值小的在文档中所占百分比
     */
    public List<Percentile> getPercenRankByPrice(String city, String region, String companyType, String cotype, String degree, String workyear, String companySize, String jobTerm, String field, double[] valus) {
        BoolQueryBuilder boolQueryBuilder = getNativeBuilder(city, region, companyType, cotype, degree, workyear, companySize, jobTerm);
        if (city != null) {
            boolQueryBuilder.must(QueryBuilders.termQuery("city", city));
        }
        PercentileRanks ranks = ElasticsearchUtil.getAggPercentilesResult(boolQueryBuilder, null, field, valus, Qiancheng.class);
        Iterator<Percentile> iterator = ranks.iterator();
        List<Percentile> percentiles = Lists.newArrayList(iterator);
        return percentiles;
    }

    public void getCountByEveryMonth(String year) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        String indexName = "qiancheng-" + year + "*";
    }


    /**
     * @param city
     * @param from
     * @param to
     * @param field
     * @Author: 张鸿建
     * @Date: 2019/10/25
     * @Desc: 获取price在一个范围内的所占比
     */
    public Map<String, Integer> getPriceRangeBucketByField(String city, int from, int to, String field) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if (city != null) {
            boolQueryBuilder.must(QueryBuilders.termQuery("city", city));
        }
        RangeAggregationBuilder rangeAggregationBuilder = AggregationBuilders.range(field).addRange(from, to);
        Range range = ElasticsearchUtil.getAggRangeResult(boolQueryBuilder, null, rangeAggregationBuilder, Qiancheng.class);

        Map<String, Integer> hashMap = new HashMap<>();
        range.getBuckets().forEach(bucket -> {
            hashMap.put(bucket.getKeyAsString(), new Long(bucket.getDocCount()).intValue());
        });
        return hashMap;
    }

    /**
     * @param city
     * @param from
     * @param to
     * @param field
     * @Author: 张鸿建
     * @Date: 2019/10/25
     * @Desc: 根据年限范围去获取文档数
     */
    public Map<String, Integer> getDateRangeBucketByField(String city, String region, String companyType, String cotype, String degree, String workyear, String companySize, String jobTerm, int from, int to, String field) {
        BoolQueryBuilder boolQueryBuilder = getNativeBuilder(city, region, companyType, cotype, degree, workyear, companySize, jobTerm);
        if (city != null) {
            boolQueryBuilder.must(QueryBuilders.termQuery("city", city));
        }
        DateRangeAggregationBuilder dateRangeAggregationBuilder = AggregationBuilders.dateRange(field).addRange(from, to);
        Range range = ElasticsearchUtil.getAggDateResult(boolQueryBuilder, null, dateRangeAggregationBuilder, Qiancheng.class);
        List<? extends Range.Bucket> buckets = range.getBuckets();
        Map<String, Integer> hashMap = new HashMap<>();
        buckets.forEach(bucket -> {
            hashMap.put(bucket.getKeyAsString(), new Long(bucket.getDocCount()).intValue());
        });
        return hashMap;
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
     * @Date: 2019/10/25
     * @Desc:
     */
    public int getCountByQuery(String city, String region, String companyType, String cotype, String degree, String workyear, String companySize, String jobTerm) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = getNativeBuilder(city, region, companyType, cotype, degree, workyear, companySize, jobTerm);
        NativeSearchQuery build = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).build();
        long count = elasticsearchTemplate.count(build, Qiancheng.class);
        return new Long(count).intValue();
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
     * @Date: 2019/10/29
     * @Desc:
     */
    public int getTodayCount(String city, String region, String companyType, String cotype, String degree, String workyear, String companySize, String jobTerm) {
        BoolQueryBuilder boolQueryBuilder = getNativeBuilder(city, region, companyType, cotype, degree, workyear, companySize, jobTerm);
        boolQueryBuilder.must(QueryBuilders.rangeQuery("time").lte(DateStringUtil.getThisDay()).gte(DateStringUtil.getThisYestoday()));
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        NativeSearchQuery build = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).build();
        long count = elasticsearchTemplate.count(build, Qiancheng.class);
        return new Long(count).intValue();
    }

    public Map getPercentilesRank(String city, String region, String companyType, String cotype, String degree, String workyear, String companySize, String jobTerm, String field, double[] values) {
        BoolQueryBuilder boolQueryBuilder = getNativeBuilder(city, region, companyType, cotype, degree, workyear, companySize, jobTerm);
        PercentileRanksAggregationBuilder agg = AggregationBuilders.percentileRanks("agg", values).field(field);
        PercentileRanks aggPercentilesResult = ElasticsearchUtil.getAggPercentilesResult(boolQueryBuilder, null, field, values, Qiancheng.class);
        Map<Object, Object> hashMap = new HashMap<>();
        for (double value : values) {
            double percent = aggPercentilesResult.percent(value);
            percent = (double) Math.round(percent * 100) / 100;
            hashMap.put(value, percent);
        }
        return hashMap;
    }

    public JSONArray getBrokenLine(String dateType, String queryField) {
        DateHistogramInterval dateHistogramInterval = getDateHistogramInterval(dateType);
        DateHistogramAggregationBuilder dateHistogramAggregationBuilder = AggregationBuilders.dateHistogram("agg").field(queryField).dateHistogramInterval(dateHistogramInterval);
        TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms("agg").field("city");
        MultiBucketsAggregation subAggDateHistogramResult = ElasticsearchUtil.getSubAggDateHistogramResult(dateHistogramAggregationBuilder, termsAggregationBuilder, Qiancheng.class);
        JSONArray array = new JSONArray();
        subAggDateHistogramResult.getBuckets().forEach(bucket -> {
            String keyAsString = bucket.getKeyAsString();
            String dateStr = keyAsString.split("T")[0];
            if ("MONTH".equals(dateType)) {
                dateStr = dateStr.substring(0, dateStr.length() - 3);
            }else if("YEAR".equals(dateType)){
                dateStr = dateStr.substring(0, dateStr.length() - 6);
            }
            JSONObject subJsonObject = new JSONObject();
            subJsonObject.put("queryDate", dateStr);
            MultiBucketsAggregation aggregations = (MultiBucketsAggregation) bucket.getAggregations().get("agg");
            aggregations.getBuckets().forEach(subBucket -> {
                String city = subBucket.getKeyAsString();
                long docCount = subBucket.getDocCount();
                subJsonObject.put(city, new Long(docCount));
            });
            array.add(subJsonObject);
        });
        return array;
    }

    private DateHistogramInterval getDateHistogramInterval(String queryType) {
        if (queryType == null) {
            throw new NotifyException("query Type error");
        }
        DateHistogramInterval histogram;
        switch (queryType) {
            case "SECOND":
                histogram = DateHistogramInterval.SECOND;
                break;
            case "MINUTE":
                histogram = DateHistogramInterval.MINUTE;
                break;
            case "HOUR":
                histogram = DateHistogramInterval.HOUR;
                break;
            case "DAY":
                histogram = DateHistogramInterval.DAY;
                break;
            case "WEEK":
                histogram = DateHistogramInterval.WEEK;
                break;
            case "MONTH":
                histogram = DateHistogramInterval.MONTH;
                break;
            case "QUARTER":
                histogram = DateHistogramInterval.QUARTER;
                break;
            case "YEAR":
                histogram = DateHistogramInterval.YEAR;
                break;
            default:
                throw new RuntimeException("时间类型解析错误");
        }
        return histogram;
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
     * @Date: 2019/10/29
     * @Desc:
     */
    public HashMap getCountByHistogram(String city, String region, String companyType, String cotype, String degree, String workyear, String companySize, String jobTerm, String field, String queryType) {
        BoolQueryBuilder nativeBuilder = getNativeBuilder(city, region, companyType, cotype, degree, workyear, companySize, jobTerm);

        DateHistogramInterval histogram = getDateHistogramInterval(queryType);
        DateHistogramAggregationBuilder dateHistogramAggregationBuilder = AggregationBuilders.dateHistogram("agg").dateHistogramInterval(histogram).field(field);
        MultiBucketsAggregation range = ElasticsearchUtil.getAggDateHistogramResult(nativeBuilder, null, dateHistogramAggregationBuilder, Qiancheng.class);
        HashMap<String, Integer> hashMap = new HashMap<>();
        range.getBuckets().forEach(bucket -> {
            String dateStr = formatDate(bucket.getKeyAsString());
            hashMap.put(dateStr, new Long(bucket.getDocCount()).intValue());
        });
        return hashMap;
    }

    private BoolQueryBuilder getNativeBuilder(String city, String region, String companyType, String cotype, String degree, String workyear, String companySize, String jobTerm) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if (city != null) {
            boolQueryBuilder.must(QueryBuilders.termQuery("city", city));
        }
        if (region != null) {
            boolQueryBuilder.must(QueryBuilders.termQuery("region", region));
        }
        if (companyType != null) {
            boolQueryBuilder.must(QueryBuilders.termQuery("companyType", companyType));
        }
        if (cotype != null) {
            boolQueryBuilder.must(QueryBuilders.termQuery("cotype", cotype));
        }
        if (degree != null) {
            boolQueryBuilder.must(QueryBuilders.termQuery("degree", degree));
        }
        if (workyear != null) {
            boolQueryBuilder.must(QueryBuilders.termQuery("workyear", workyear));
        }
        if (companySize != null) {
            boolQueryBuilder.must(QueryBuilders.termQuery("companySize", companySize));
        }
        if (jobTerm != null) {
            boolQueryBuilder.must(QueryBuilders.termQuery("jobTerm", jobTerm));
        }

        return boolQueryBuilder;
    }

    private String formatDate(String dateStr) {
        if (dateStr.contains("T")) {
            String[] ts = dateStr.split("T");
            return ts[0];
        } else {
            return dateStr;
        }
    }
}
