/*
 * Copyright (c) 2018年4月27日 by XuanWu Wireless Technology Co.Ltd. 
 *             All rights reserved                         
 */
package cn.esports.entity.dto;
/**
* @Description 
* @author <a href="mailto:zhangxuwei@wxchina.com">zhangxuwei</a>
* @date 2018年4月27日
* @version 1.0.0
*/
public class LiveStreamDto {
	private String liveStreamName;
	private String gameType;
	private String status;
	private Integer limit;
	private Long offset;
	public String getLiveStreamName() {
		return liveStreamName;
	}
	public void setLiveStreamName(String liveStreamName) {
		this.liveStreamName = liveStreamName;
	}
	public String getGameType() {
		return gameType;
	}
	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Long getOffset() {
		return offset;
	}
	public void setOffset(Long offset) {
		this.offset = offset;
	}
	
	
	
}
