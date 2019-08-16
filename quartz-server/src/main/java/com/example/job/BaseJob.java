package com.example.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author zhanghongjian
 * @Date 2019/5/7 13:47
 * @Description
 */
public interface BaseJob extends Job {

    public void execute(JobExecutionContext context) throws JobExecutionException;
}
