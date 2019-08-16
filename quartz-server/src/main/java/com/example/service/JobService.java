package com.example.service;/**
 * ${tag}
 *
 * @author zhanghongjian
 * @Date 2019/5/22 16:25
 */

import com.example.entity.JobInfo;
import com.example.job.NewJob;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @author：张鸿建
 * @time：2019/5/22
 * @desc：
 **/
@Service
public class JobService {
    private static Logger log = LoggerFactory.getLogger(JobService.class);
    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;

    @Autowired
    RestTemplate restTemplate;

    public void addjob(JobInfo jobInfo) throws Exception {
        if ("".equals(jobInfo.getJobClassName()) || "".equals(jobInfo.getJobGroupName()) || "".equals(jobInfo.getCronExpression())) {
            return;
        }

        String info = restTemplate.getForObject("http://hi-service/hi?name=" + "ali", String.class);
        log.info("info     ...        "+info);
        if (jobInfo.getTimeType() == null) {
            addCronJob(jobInfo);
            return;
        }
    }

    public void updateJob(JobInfo jobInfo) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobInfo.getJobClassName(), jobInfo.getJobGroupName());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                    .withSchedule(CronScheduleBuilder.cronSchedule(jobInfo.getCronExpression())).build();
            // 重启触发器
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void deleteJob(JobInfo jobInfo) {
        try {
            scheduler.deleteJob(new JobKey(jobInfo.getJobClassName(), jobInfo.getJobGroupName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void pauseJob(JobInfo jobInfo) {
        try {
            JobKey jobKey = JobKey.jobKey(jobInfo.getJobClassName(), jobInfo.getJobGroupName());
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    public void resumeJob( JobInfo jobInfo) {
        try {
            JobKey jobKey = JobKey.jobKey(jobInfo.getJobClassName(), jobInfo.getJobGroupName());
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void runJobNow(JobInfo jobInfo) {
        try {
            JobKey jobKey = JobKey.jobKey(jobInfo.getJobClassName(), jobInfo.getJobGroupName());
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    public List<Map<String, Object>> queryAllJob() {
        List<Map<String, Object>> jobList = null;
        try {
            GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
            Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
            jobList = new ArrayList<Map<String, Object>>();
            for (JobKey jobKey : jobKeys) {
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                for (Trigger trigger : triggers) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("jobName", jobKey.getName());
                    map.put("jobGroupName", jobKey.getGroup());
                    map.put("description", "触发器:" + trigger.getKey());
                    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                    map.put("jobStatus", triggerState.name());
                    if (trigger instanceof CronTrigger) {
                        CronTrigger cronTrigger = (CronTrigger) trigger;
                        String cronExpression = cronTrigger.getCronExpression();
                        map.put("jobTime", cronExpression);
                    }
                    jobList.add(map);
                }
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return jobList;
    }

    public List<Map<String, Object>> queryRunJob() {
        List<Map<String, Object>> jobList = null;
        try {
            List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
            jobList = new ArrayList<Map<String, Object>>(executingJobs.size());
            for (JobExecutionContext executingJob : executingJobs) {
                Map<String, Object> map = new HashMap<String, Object>();
                JobDetail jobDetail = executingJob.getJobDetail();
                JobKey jobKey = jobDetail.getKey();
                Trigger trigger = executingJob.getTrigger();
                map.put("jobName", jobKey.getName());
                map.put("jobGroupName", jobKey.getGroup());
                map.put("description", "触发器:" + trigger.getKey());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                map.put("jobStatus", triggerState.name());
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    map.put("jobTime", cronExpression);
                }
                jobList.add(map);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return jobList;
    }

    public void addCronJob(JobInfo jobInfo) throws Exception {
        System.out.println("addCronJob             ...");
        // 启动调度器
        scheduler.start();

        JobDataMap dataMap = new JobDataMap();
        dataMap.put("type","this is dataMap");

        //构建job信息
        JobDetail jobDetail = JobBuilder.newJob(new NewJob().getClass()).
                withIdentity(jobInfo.getJobClassName(), jobInfo.getJobGroupName()).setJobData(dataMap)
                .build();

        //表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(jobInfo.getCronExpression());
        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().
                withIdentity(jobInfo.getJobClassName(), jobInfo.getJobGroupName())
                .withSchedule(scheduleBuilder)
                .build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);

        } catch (SchedulerException e) {
            System.out.println("创建定时任务失败" + e);
            throw new Exception("创建定时任务失败");
        }
    }

    public void addSimpleJob(JobInfo jobInfo) throws Exception {
        // 启动调度器
        System.out.println("addSimpleJob              ...");
        scheduler.start();
        //构建job信息
        JobDetail jobDetail = JobBuilder.newJob(new NewJob().getClass())
                .withIdentity(jobInfo.getJobClassName(), jobInfo.getJobGroupName())
                .build();

        SimpleTrigger simpleTrigger = (SimpleTrigger) TriggerBuilder.newTrigger()
                .withIdentity(jobInfo.getJobClassName(), jobInfo.getJobGroupName())
                .startNow()
                .forJob(jobInfo.getJobClassName(), jobInfo.getJobGroupName())
                .build();

        try {
            scheduler.scheduleJob(jobDetail, simpleTrigger);

        } catch (SchedulerException e) {
            System.out.println("创建定时任务失败" + e);
            throw new Exception("创建定时任务失败");
        }
    }


}
