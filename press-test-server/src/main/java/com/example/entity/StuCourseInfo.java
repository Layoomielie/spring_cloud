package com.example.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

/**
 * @PackageName lano.app.jxxt.entity
 * @ClassName StuCourseInfo
 * @Author Zhanxc
 * @CreateTime 2019-05-22
 * @Description TODO 学员课程信息类，替换原 TXyKc 类
 * @Version 1.0
 **/


@Document(indexName = "stu_course_info", type = "doc")
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class StuCourseInfo {

    //分课类型 0：默认，用户自选课程     1:管理员后台手动分课，Excel导入分课等
	public static final Integer COURSE_DIVISION_TYPE_OPTIONAL = 0;
	public static final Integer COURSE_DIVISION_TYPE_EXCEL = 1;

	// 学员编号
	private String stuId;

	// 班级编号 有通过班级编号查询学生课程信息的需求，先保留
	private String clazzId;

	// 课程ID
	private String courseId;

	// 课程名称
	private String courseName;

	// 单位编号
	private String corpId;

	// 是否必修（否则选修）
	private Integer courseRequired;

	// 各课时学习次数 key/value模式 {chapterId:count}
	private String chapterLearnedCount;

	// 当前正在学习的课时 (chapterId)
	private String currentChapter;

	// 已完成的课时 （视频、音频、文本等）(chapterId1,chapterId2...)
	private String chapterDone;

	// 已完成的作业 ==》 作业类型试卷 ==》paperId
	private String homeworkDone;

	// 已完成的考试 ==》 考试类型试卷 ==》paperId
	private String examDone;

	// 已完成的测验 ==》 测验类型试卷 ==》 paperId
	private String testDone;

	// 学习中的课时 {chapterId01,chapterId02...}
	private String chapterLearning;

	// 学习中视频时长记录 chpaterId:time     // time 为second
	private String videoTrack;

	// 学习中视频进度记录 chpaterId:proportion
	private String videoProgress;

	// 学习中音频记录：
	private String audioTrack;

	// 音频进度：
	private String audioProgress;

	// 是否已选
	// 必修课自动设置为已选择
	// 选修课当学员选择完毕后，设置为已选状态
	// 0- 为选, 1-已选
	private Integer courseChoosed;

	// 上次学习时间
	private Date lastTime;

	// 是否学完该课程
	private Integer courseFinished;

	// 是否通过课程考核
	private Integer coursePassed;

	// 分课类型 0：默认，用户自选课程 1:管理员后台手动分课，Excel导入分课等
	private Integer courseDivisionType;

	// 学员名称
	private String stuName;
	// 学员邮箱
	String stuEmail;
	// 学员电话
	String stuPhoneNum;

	// 班级名称
	private String clazzName;

	// 学科名称
	private String subjectName;

	// 课程图片URL
	private String courseImgUrl;

	// 讲师姓名
	private String teacherName;

	// 课程是否已发布
	private Integer coursePublished;

	// 课程发布状态（0：未发布，1：已发布，2：待审核）
	private Integer publishStatus;

	// 课程状态（0：正常；1：未开始；2：已结束；3：结课）
	private Integer courseStatus;

	// 课程分类 (所属学科）
	private String courseCategory;

	private Date startDate; // 课程开始时间

	private Date endDate; // 课程结束时间

	private Double totalProgress; // 学员课程学习总进度
	private String courseProgress; // 课程开课进度

	public String getCourseProgress() {
		return courseProgress;
	}

	public void setCourseProgress(String courseProgress) {
		this.courseProgress = courseProgress;
	}

	public Double getTotalProgress() {
		return totalProgress;
	}

	public void setTotalProgress(Double totalProgress) {
		this.totalProgress = totalProgress;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getStuEmail() {
		return stuEmail;
	}

	public void setStuEmail(String stuEmail) {
		this.stuEmail = stuEmail;
	}

	public String getStuPhoneNum() {
		return stuPhoneNum;
	}

	public void setStuPhoneNum(String stuPhoneNum) {
		this.stuPhoneNum = stuPhoneNum;
	}

	public String getClazzId() {
		return clazzId;
	}

	public void setClazzId(String clazzId) {
		this.clazzId = clazzId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Integer getCourseRequired() {
		return courseRequired;
	}

	public void setCourseRequired(Integer courseRequired) {
		this.courseRequired = courseRequired;
	}

	public String getCourseCategory() {
		return courseCategory;
	}

	public void setCourseCategory(String courseCategory) {
		this.courseCategory = courseCategory;
	}

	public Integer getCourseDivisionType() {
		return courseDivisionType;
	}

	public void setCourseDivisionType(Integer courseDivisionType) {
		this.courseDivisionType = courseDivisionType;
	}

	public String getChapterLearnedCount() {
		return chapterLearnedCount;
	}

	public void setChapterLearnedCount(String chapterLearnedCount) {
		this.chapterLearnedCount = chapterLearnedCount;
	}

	public String getCurrentChapter() {
		return currentChapter;
	}

	public void setCurrentChapter(String currentChapter) {
		this.currentChapter = currentChapter;
	}

	public String getChapterDone() {
		return chapterDone;
	}

	public void setChapterDone(String chapterDone) {
		this.chapterDone = chapterDone;
	}

	public String getHomeworkDone() {
		return homeworkDone;
	}

	public void setHomeworkDone(String homeworkDone) {
		this.homeworkDone = homeworkDone;
	}

	public String getExamDone() {
		return examDone;
	}

	public void setExamDone(String examDone) {
		this.examDone = examDone;
	}

	public String getTestDone() {
		return testDone;
	}

	public void setTestDone(String testDone) {
		this.testDone = testDone;
	}

	public String getChapterLearning() {
		return chapterLearning;
	}

	public void setChapterLearning(String chapterLearning) {
		this.chapterLearning = chapterLearning;
	}

	public String getVideoTrack() {
		return videoTrack;
	}

	public void setVideoTrack(String videoTrack) {
		this.videoTrack = videoTrack;
	}

	public String getVideoProgress() {
		return videoProgress;
	}

	public void setVideoProgress(String videoProgress) {
		this.videoProgress = videoProgress;
	}

	public String getAudioTrack() {
		return audioTrack;
	}

	public void setAudioTrack(String audioTrack) {
		this.audioTrack = audioTrack;
	}

	public String getAudioProgress() {
		return audioProgress;
	}

	public void setAudioProgress(String audioProgress) {
		this.audioProgress = audioProgress;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public String getClazzName() {
		return clazzName;
	}

	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getCourseImgUrl() {
		return courseImgUrl;
	}

	public void setCourseImgUrl(String courseImgUrl) {
		this.courseImgUrl = courseImgUrl;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public Integer getCoursePublished() {
		return coursePublished;
	}

	public void setCoursePublished(Integer coursePublished) {
		this.coursePublished = coursePublished;
	}

	public Integer getPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(Integer publishStatus) {
		this.publishStatus = publishStatus;
	}

	public Integer getCourseChoosed() {
		return courseChoosed;
	}

	public void setCourseChoosed(Integer courseChoosed) {
		this.courseChoosed = courseChoosed;
	}

	public Integer getCourseFinished() {
		return courseFinished;
	}

	public void setCourseFinished(Integer courseFinished) {
		this.courseFinished = courseFinished;
	}

	public Integer getCoursePassed() {
		return coursePassed;
	}

	public void setCoursePassed(Integer coursePassed) {
		this.coursePassed = coursePassed;
	}

	public Integer getCourseStatus() {
		return courseStatus;
	}

	public void setCourseStatus(Integer courseStatus) {
		this.courseStatus = courseStatus;
	}

	@Override
	public String toString() {
		return "StuCourseInfo{" + "stuId='" + stuId + '\'' + ", stuName='" + stuName + '\'' + ", stuEmail='" + stuEmail
				+ '\'' + ", stuPhoneNum='" + stuPhoneNum + '\'' + ", clazzId='" + clazzId + '\'' + ", courseId='"
				+ courseId + '\'' + ", courseName='" + courseName + '\'' + ", courseRequired=" + courseRequired
				+ ", courseCategory='" + courseCategory + '\'' + ", courseDivisionType=" + courseDivisionType
				+ ", chapterLearnedCount='" + chapterLearnedCount + '\'' + ", currentChapter='" + currentChapter + '\''
				+ ", chapterDone='" + chapterDone + '\'' + ", homeworkDone='" + homeworkDone + '\'' + ", examDone='"
				+ examDone + '\'' + ", testDone='" + testDone + '\'' + ", chapterLearning='" + chapterLearning + '\''
				+ ", videoTrack='" + videoTrack + '\'' + ", videoProgress='" + videoProgress + '\'' + ", audioTrack='"
				+ audioTrack + '\'' + ", audioProgress='" + audioProgress + '\'' + ", courseChoosed=" + courseChoosed
				+ ", lastTime=" + lastTime + ", courseFinished=" + courseFinished + ", coursePassed=" + coursePassed
				+ ", clazzName='" + clazzName + '\'' + ", subjectName='" + subjectName + '\'' + ", courseImgUrl='"
				+ courseImgUrl + '\'' + ", teacherName='" + teacherName + '\'' + ", corpId='" + corpId + '\''
				+ ", coursePublished=" + coursePublished + ", publishStatus=" + publishStatus + ", courseStatus="
				+ courseStatus + "} " + super.toString();
	}
}
