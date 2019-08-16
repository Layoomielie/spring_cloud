package com.example.job;/**
 * ${tag}
 *
 * @author zhanghongjian
 * @Date 2019/5/22 19:18
 */

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * @author：张鸿建
 * @time：2019/5/22
 * @desc：
 **/
@Component
public class MyJobFactory extends AdaptableJobFactory {

    @Autowired
    private AutowireCapableBeanFactory capableBeanFactory;

    /**
      * @author：张鸿建
      * @date: 2019/5/22
      * @desc: job实例是在quartz中创建的 如果需要调用bean 则需要在创建job实例后将属性注入到Spring容器中
      **/
    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        //调用父类的方法
        Object jobInstance = super.createJobInstance(bundle);
        //进行注入,这属于Spring的技术,不清楚的可以查看Spring的API.
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}
