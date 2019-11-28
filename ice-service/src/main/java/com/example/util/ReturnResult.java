package com.example.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * 返回结果类
 * 
 * @author huxg
 *
 */
@ApiModel(value = "返回结果", description = "本框架通用返回结果包装类")
public class ReturnResult implements Serializable {
	@ApiModelProperty(value = "返回代码,返回代码在系统中是唯一的，用于标识某一次操作的返回。", dataType = "int", name = "resultCode")
	int resultCode = -1;

	@ApiModelProperty(value = "操作执行是否成功,用于标识本次操作是否执行成功。", dataType = "boolean", name = "success")
	boolean success = false;

	@ApiModelProperty(value = "返回的消息,出错时，为错误信息，成功时为成功提示信息。", dataType = "String", name = "message")
	String message;

	@ApiModelProperty(value = "返回的数据,可能是json数组，也可能是json对象，根据所执行的操作返回。", dataType = "Object", name = "data")
	Object data;

	@ApiModelProperty(value = "返回的数据,分页信息。", dataType = "PageInfo", name = "page")
	PageInfo page;

	@ApiModelProperty(value = "用于标识是哪个属性出的错误。", dataType = "String", name = "field")
	String field;

	/**
	 * 
	 */
	private static final long serialVersionUID = 7435327922483479823L;

	public static ReturnResult ok() {
		return ok(null);
	}

	public static ReturnResult ok(Object obj) {
		return ok(0, "", obj);
	}

	public static ReturnResult ok(Object obj, PageInfo pageInfo) {
		ReturnResult rr = ok(0, "", obj);
		if (pageInfo != null)
			rr.page = pageInfo;
		return rr;
	}
	public static ReturnResult ok(Object obj, PageInfo pageInfo,String message) {
		ReturnResult rr = ok(0, message, obj);
		if (pageInfo != null)
			rr.page = pageInfo;
		return rr;
	}

	public static ReturnResult ok(int resultCode, String message, Object obj) {
		return createResult(resultCode, true, message, obj);
	}

	public static ReturnResult ok(String[] keys, String[] values) {
		JSONObject jo = new JSONObject();
		if (keys != null && values != null && keys.length == values.length) {
			for (int i = 0; i < keys.length; i++) {
				jo.put(keys[i], values[i]);
			}
		}
		ReturnResult r = ok();
		r.data = jo.toJSONString();
		return r;
	}

	public static ReturnResult okMessage(String message) {
		return ok(0, message, null);
	}

	public static ReturnResult error() {
		ReturnResult r = new ReturnResult();
		r.success = false;
		r.resultCode = -1;
		r.data = "";
		r.message = "操作失败";
		return r;
	}

	public static ReturnResult error(Object obj) {
		ReturnResult r = error();
		if (obj instanceof String) {
			r.data = (String) obj;
		} else {
			r.data = JSON.toJSONString(obj);
		}
		return r;
	}

	private static ReturnResult createResult(int resultCode, boolean success, String message, Object obj) {
		ReturnResult r = new ReturnResult();
		r.resultCode = resultCode;
		r.success = success;
		if (success)
			r.message = StringUtils.isEmpty(message) ? "操作成功" : message;
		else
			r.message = StringUtils.isEmpty(message) ? "操作失败" : message;
		r.data = obj == null ? "" : obj;
		return r;
	}

	public static ReturnResult errorMessage(String message) {
		ReturnResult r = error();
		r.message = message;
		return r;
	}

	public static ReturnResult errorMessage(int errorCode, String message) {
		ReturnResult r = error();
		r.message = message;
		r.resultCode = errorCode;
		return r;
	}

	public static ReturnResult errorMessage(int errorCode, String field, String message) {
		ReturnResult r = error();
		r.message = message;
		r.field = field;
		r.resultCode = errorCode;
		return r;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public PageInfo getPage() {
		return page;
	}

	public void setPage(PageInfo page) {
		this.page = page;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

}
