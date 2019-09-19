package com.kafka.message;

/*
* @author: zhanghongjian
* @date: 2019年5月8日
* @descript: 定时消息
*/
public class CronMessage extends BaseMessage {
	
	public final static Integer addOrUpdateJob=1;
	public final static Integer deleteJob=2;
	public final static Integer pauseJob=3;
	public final static Integer resumeJob=4;
	public final static Integer runNowJob=5;
	public final static Integer queryAllJob=6;
	public final static Integer queryRunJob=7;
	// 定时表达式
	private String cornExpress;
	// 触发器名称
	private String triggerName;
    // 触发器组
	private String triggerGroup;
    // 任务名称
	private String jobName;
	// 任务组
	private String jobGroup;
	// 设置任务类型
	private Integer jobType;


	public Integer getJobType() {
		return jobType;
	}

	public void setJobType(Integer jobType) {
		this.jobType = jobType;
	}

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public String getTriggerGroup() {
		return triggerGroup;
	}

	public void setTriggerGroup(String triggerGroup) {
		this.triggerGroup = triggerGroup;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getCornExpress() {
		return cornExpress;
	}

	public void setCornExpress(String cornExpress) {
		this.cornExpress = cornExpress;
	}

}
