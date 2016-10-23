package com.ipro.survey.web.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.sql.Timestamp;

public class JsonResult<T> implements Serializable {
	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 是否成功
	 */
	private Boolean ret = Boolean.TRUE;
	/**
	 * 错误信息
	 */
	private String errmsg;
	/**
	 * 错误码
	 */
	private Integer errcode;
	/**
	 * 数据
	 */
	private T data;
	
	/**
	 * 当前服务器时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Timestamp currentTime = new Timestamp(System.currentTimeMillis());
	
	public JsonResult() {
	}

	public static JsonResult successJsonResult() {
		JsonResult jsonResult = new JsonResult();
		jsonResult.setRet(Boolean.TRUE);
		return jsonResult;
	}

    public static <T> JsonResult successJsonResult(T data) {
        JsonResult jsonResult = new JsonResult();
		jsonResult.setRet(Boolean.TRUE);
		jsonResult.setData(data);
		return jsonResult;
    }

    public static <T> JsonResult failureJsonResult(String errmsg, Integer errcode) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setErrmsg(errmsg);
        jsonResult.setErrcode(errcode);
        jsonResult.setRet(Boolean.FALSE);
        return jsonResult;
    }

	public static <T> JsonResult failureJsonResult(String errmsg ) {
		JsonResult jsonResult = new JsonResult();
		jsonResult.setErrmsg(errmsg);
		jsonResult.setRet(Boolean.FALSE);
		return jsonResult;
	}

	public JsonResult(Boolean ret, String errmsg, T data) {
		super();
		this.ret = ret;
		this.errmsg = errmsg;
		this.data = data;
	}
	public Boolean getRet() {
		return ret;
	}

	public void setRet(Boolean ret) {
		this.ret = ret;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public Integer getErrcode() {
		return errcode;
	}

	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}



	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

    public Timestamp getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Timestamp currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
