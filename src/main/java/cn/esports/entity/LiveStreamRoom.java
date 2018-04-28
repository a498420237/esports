/*
 * Copyright (c) 2018年4月26日 by XuanWu Wireless Technology Co.Ltd. 
 *             All rights reserved                         
 */
package cn.esports.entity;
/**
* @Description 直播列表对象
* @author <a href="mailto:zhangxuwei@wxchina.com">zhangxuwei</a>
* @date 2018年4月26日
* @version 1.0.0
*/
public class LiveStreamRoom {
	 private String contentRelation;
	 private int gameType;
     private int id;
     private String liveStreamId;
     private String liveStreamName;
     private String pictureUrl;
     private int rank;
     private int  relationId;
     private String status;
     private String vedioStreamAddr;
     private String gameName;
     
	public String getContentRelation() {
		return contentRelation;
	}
	public void setContentRelation(String contentRelation) {
		this.contentRelation = contentRelation;
	}
	public int getGameType() {
		return gameType;
	}
	public void setGameType(int gameType) {
		this.gameType = gameType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLiveStreamId() {
		return liveStreamId;
	}
	public void setLiveStreamId(String liveStreamId) {
		this.liveStreamId = liveStreamId;
	}
	public String getLiveStreamName() {
		return liveStreamName;
	}
	public void setLiveStreamName(String liveStreamName) {
		this.liveStreamName = liveStreamName;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getRelationId() {
		return relationId;
	}
	public void setRelationId(int relationId) {
		this.relationId = relationId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVedioStreamAddr() {
		return vedioStreamAddr;
	}
	public void setVedioStreamAddr(String vedioStreamAddr) {
		this.vedioStreamAddr = vedioStreamAddr;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
     
     
     
}
