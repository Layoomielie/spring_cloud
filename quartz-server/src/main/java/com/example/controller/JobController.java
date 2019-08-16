package com.example.controller;/**
 * ${tag}
 *
 * @author zhanghongjian
 * @Date 2019/5/22 16:22
 */

import com.example.entity.JobInfo;
import com.example.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author：张鸿建
 * @time：2019/5/22
 * @desc：
 **/
@RestController
@RequestMapping(value = "job/")
public class JobController {

    @Autowired
    JobService jobService;

    @PostMapping(value = "addjob")
    public void addJob(@RequestBody JobInfo jobInfo) throws  Exception{
        jobService.addjob(jobInfo);
    }

    @PostMapping(value = "updatejob")
    public void updateJob(@RequestBody JobInfo jobInfo) {
        jobService.updateJob(jobInfo);
    }

    @PostMapping(value = "deletejob")
    public void deleteJob(@RequestBody JobInfo jobInfo) {
        jobService.deleteJob(jobInfo);
    }
    @PostMapping(value = "pauseJob")
    public void pauseJob(@RequestBody JobInfo jobInfo) {
        jobService.pauseJob(jobInfo);
    }
    @PostMapping(value="resumeJob")
    public void resumeJob(@RequestBody JobInfo jobInfo) {
        jobService.resumeJob(jobInfo);
    }
    @PostMapping(value="runJobNow")
    public void runJobNow(@RequestBody JobInfo jobInfo) {
        jobService.runJobNow(jobInfo);
    }
    @RequestMapping(value = "AllJob")
    public List<Map<String, Object>> queryAllJob() {
        return jobService.queryAllJob();
    }
    @RequestMapping(value = "AllRunJob")
    public List<Map<String, Object>> queryRunJob() {
        return jobService.queryRunJob();
    }
}
