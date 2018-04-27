/*
 * Copyright (c) 2018年4月26日 by XuanWu Wireless Technology Co.Ltd. 
 *             All rights reserved                         
 */
package cn.esports.service;

import java.util.List;

import cn.esports.entity.LiveStreamRoom;
import cn.esports.entity.base.RestEntity;

/**
* @Description 
* @author <a href="mailto:zhangxuwei@wxchina.com">zhangxuwei</a>
* @date 2018年4月26日
* @version 1.0.0
*/
public interface LiveStreamRoomService {
	/**
	 * 根据条件获取直播列表
	 * @param liveStreamName
	 * @param gameType
	 * @param status
	 * @param limit
	 * @param offset
	 * @return
	 */
	public RestEntity<LiveStreamRoom> getLiveStreamRoom(String liveStreamName,String gameType,String status,Long offset,Integer limit);
}
