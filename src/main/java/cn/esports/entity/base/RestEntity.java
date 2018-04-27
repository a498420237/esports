/*
 * Copyright (c) 2018年4月26日 by XuanWu Wireless Technology Co.Ltd. 
 *             All rights reserved                         
 */
package cn.esports.entity.base;

import cn.esports.entity.JsonResp;
import cn.esports.entity.MatchInfo.TBean;

/**
* @Description 
* @author <a href="mailto:zhangxuwei@wxchina.com">zhangxuwei</a>
* @date 2018年4月26日
* @version 1.0.0
*/
public class RestEntity<T> {
	public static final int SUCCESS_STATUS = 0;
	public static final int DEFAULT_FAIL_STATUS = -1;
	
	
	private String msg;
	private int code;
	private ResultInfo<T> t;

	public RestEntity() {
		super();
	}
	public RestEntity(String msg, int code) {
		super();
		this.msg = msg;
		this.code = code;
	}
	
	public RestEntity(int code, ResultInfo<T> t) {
		super();
		this.code = code;
		this.t = t;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public ResultInfo<T> getT() {
		return t;
	}
	public void setT(ResultInfo<T> t) {
		this.t = t;
	}
	
	public static RestEntity fail(String msg){
		return new RestEntity(msg,DEFAULT_FAIL_STATUS);
	}
	
}
