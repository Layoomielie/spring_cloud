package com.example.thread;

import com.example.util.SpringUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author：张鸿建
 * @time：2019/11/29 13:42
 * @desc：
 **/
public class PressureThread implements Runnable {

    private Logger logger = LoggerFactory.getLogger(PressureThread.class);

    private ElasticsearchTemplate elasticsearchTemplate;

    private PressureMethod pressureMethod;

    // 1 为after查询 2 为简单查询  3 为修改  4为删除
    private int flag;

    public PressureThread(PressureMethod pressureMethod, int flag) {
        this.pressureMethod = pressureMethod;
        this.flag = flag;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 线程开始查询！！");
        System.out.println(pressureMethod == null);
        if (this.flag == 1) {
            pressureMethod.cyclicQuery(null);
        } else if (this.flag == 2) {
            for (int i = 0; i < 10000; i++) {
                pressureMethod.cyclicSimpleQuery();
            }

        }
        System.out.println(Thread.currentThread().getName() + " 线程执行完成");

    }


}
