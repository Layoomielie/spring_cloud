package com.example.thread;

import com.example.entity.StuCourseInfo;
import com.example.util.SpringUtils;
import com.google.common.collect.Lists;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author：张鸿建
 * @time：2019/11/29 13:49
 * @desc：
 **/
public class PressureMethod {
    private Logger logger = LoggerFactory.getLogger(PressureMethod.class);

    private String indexName;

    private AtomicInteger count = new AtomicInteger(0);

    private ElasticsearchTemplate elasticsearchTemplate;

    private ReentrantLock lock = new ReentrantLock();

    private NativeSearchQueryBuilder nativeSearchQueryBuilder;

    private CyclicBarrier cyclicBarrier;

    public PressureMethod(String indexName, int poolCount) {
        this.cyclicBarrier = new CyclicBarrier(poolCount, new Runnable() {
            @Override
            public void run() {
                logger.error("一共" + poolCount + "个线程查询完成" + ",一共查出" + count.get() + "条数据");
                System.out.println("一共" + poolCount + "个线程查询完成" + ",一共查出" + count.get() + "条数据");
            }
        });
        this.elasticsearchTemplate = SpringUtils.getBean(ElasticsearchTemplate.class);
        this.indexName = indexName;
        this.nativeSearchQueryBuilder = this.setBoolQuery();
    }

    public void cyclicQuery(Object[] sortValues) {
        SearchRequestBuilder searchRequestBuilder = elasticsearchTemplate.getClient().prepareSearch(indexName);
        searchRequestBuilder.addSort(SortBuilders.fieldSort("_id"));
        searchRequestBuilder.setSize(100);
        if (sortValues != null) {
            searchRequestBuilder.searchAfter(sortValues);
        }
        SearchResponse response = searchRequestBuilder.execute().actionGet();
        SearchHit[] hits = response.getHits().getHits();
        Object[] sortValue = null;
        ArrayList<Object> list = new ArrayList<>();
        if (hits == null || hits.length == 0) {
            try {
                cyclicBarrier.await();
                return;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        } else {
            lock.lock();
            count.addAndGet(hits.length);
            lock.unlock();
        }
        for (int i = 0; i < hits.length; i++) {
            String sourceAsString = hits[i].getSourceAsString();
            if (i == hits.length - 1) {
                sortValue = hits[i].getSortValues();
            }
            list.add(sourceAsString);
        }

        System.out.println("current " + Thread.currentThread().getName() + " search " + hits.length + " count data :" + list.toString());
        //logger.debug("current search " + hits.length + " count data :" + list.toString());
        cyclicQuery(sortValue);
    }

    public void cyclicSimpleQuery() {
        Page<StuCourseInfo> stuCourseInfos = elasticsearchTemplate.queryForPage(nativeSearchQueryBuilder.build(), StuCourseInfo.class);
        System.out.println(Thread.currentThread().getName()+" :"+Lists.newArrayList(stuCourseInfos));
    }

    public AtomicInteger getCount() {
        return count;
    }

    public NativeSearchQueryBuilder setBoolQuery() {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(QueryBuilders.termQuery("courseRequired", 0));
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(boolQueryBuilder);
        nativeSearchQueryBuilder.withPageable(PageRequest.of(0, 20));
        return nativeSearchQueryBuilder;
    }
}
