package com.kafka.message;

import java.util.HashMap;


/**
 * @author zhanghongjian
 * @Date 2019/5/8 11:41
 * @Description
 */
public class BaseMessage  {

    // status 状态码值
    public final static Integer WAITING=1;
    public final static Integer SUCESS=2;
    public final static Integer FAIL=3;
    public final static Integer ROLLBACK=4;

    //requestType 请求类型
    public final static Integer GET=1;
    public final static Integer POST=2;



    // 请求url
    // 示例 http://ddzx/job/test/message?msg={msg}
    // GET 和 POST 都可以在url后面拼接参数 设置paramMap 建议使用这个方式
    private String requestUrl;

    // 请求类型 1. get  2. post
    private Integer requestType;

    //请求参数
    private HashMap<String , Object> paramMap;

    // 请求entity
    // GET 不能使用这种参数传参
    private Object paramObject;

    // 回滚地址
    private String rollbackUrl;

    //状态码 1.请求中 2.成功 3. 失败 4. 需要回滚
    private Integer status;

    //请求返回值类型
    private Class<? extends Object> responseClazz;

    //kafka服务调用完后返回的结果信息 ,通过这个返回给微服务  如果为定时任务则返回定时任务创建信息 设置为null则不返回消息
    private String msgBackUrl;

    public Class<? extends Object> getResponseClazz() {
        return responseClazz;
    }

    public void setResponseClazz(Class<? extends Object> responseClazz) {
        this.responseClazz = responseClazz;
    }

    public String getMsgBackUrl() {
        return msgBackUrl;
    }

    public void setMsgBackUrl(String msgBackUrl) {
        this.msgBackUrl = msgBackUrl;
    }

    public HashMap<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(HashMap<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

	public Object getParamObject() {
		return paramObject;
	}

	public void setParamObject(Object paramObject) {
		this.paramObject = paramObject;
	}


	public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public Integer getRequestType() {
        return requestType;
    }

    public void setRequestType(Integer requestType) {
        this.requestType = requestType;
    }

    public String getRollbackUrl() {
        return rollbackUrl;
    }

    public void setRollbackUrl(String rollbackUrl) {
        this.rollbackUrl = rollbackUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
