package com.example.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author：张鸿建
 * @time：2019/9/23 17:24
 * @desc：
 **/
//@Configuration
public class BaseConfig {

    /**
    *
    * @Author: 张鸿建
    * @Date: 2019/9/23
    * @Desc:   如果需要使用多个cache  需要在这里注册
    */
    //@Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("sampleCache"),new ConcurrentMapCache("sampleCache2")));
        return cacheManager;
    }

}
