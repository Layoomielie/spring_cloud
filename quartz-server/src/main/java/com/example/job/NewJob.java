package com.example.job;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * @author zhanghongjian
 * @Date 2019/5/7 13:47
 * @Description
 */
public class NewJob  implements BaseJob {
    private static Logger log = LoggerFactory.getLogger(NewJob.class);

    @Autowired
    RestTemplate restTemplate;

    public NewJob() {
        //System.out.println("new Job初始化: " + new Date());
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {


        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        String type = dataMap.getString("type");
        JobDetail jobDetail = context.getJobDetail();
        JobKey key = jobDetail.getKey();
        String desc = jobDetail.getDescription();
        //System.out.println("type  ...   "+type);
        //System.out.println("new Job执行时间: " + new Date());
        String info=restTemplate.getForObject("http://hi-service/hi?name=" + "ali", String.class);
        log.info(" key: "+key+ " info: "+info+" date: "+new Date());
    }


}
