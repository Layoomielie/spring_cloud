package com.example.util;

import com.example.thread.PressureMethod;
import com.example.thread.PressureThread;
import org.apache.commons.codec.BinaryDecoder;

import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author：张鸿建
 * @time：2019/11/29 13:29
 * @desc：
 **/
public class PressureUtils {

    private ExecutorService pool;
    private String indexName;
    private int poolCount;

    public PressureUtils(int poolCount, String indexName) {
        this.pool = Executors.newFixedThreadPool(poolCount);
        this.poolCount = poolCount;
        this.indexName=indexName;
    }
    public void createQuery() {
        PressureMethod pressureMethod = new PressureMethod(indexName,poolCount);
        PressureThread pressureThread = new PressureThread(pressureMethod,1);
        for (int i = 0; i < poolCount; i++) {
            pool.execute(pressureThread);
            System.out.println("线程"+i+" 创建完成！");
        }
        pool.shutdown();
    }
    public void createSimpleQuery() {
        PressureMethod pressureMethod = new PressureMethod(null,poolCount);
        PressureThread pressureThread = new PressureThread(pressureMethod,2);
        for (int i = 0; i < poolCount; i++) {
            pool.execute(pressureThread);
            System.out.println("线程"+i+" 创建完成！");
        }
        pool.shutdown();
    }
}





