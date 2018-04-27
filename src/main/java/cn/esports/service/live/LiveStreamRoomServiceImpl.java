/*
 * Copyright (c) 2018年4月26日 by XuanWu Wireless Technology Co.Ltd. 
 *             All rights reserved                         
 */
package cn.esports.service.live;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;

import com.alibaba.fastjson.JSONObject;

import cn.esports.entity.LiveStreamRoom;
import cn.esports.entity.base.RestEntity;
import cn.esports.service.BaseService;
import cn.esports.service.LiveStreamRoomService;
import cn.esports.utils.JsonUtil;

/**
* @Description 
* @author <a href="mailto:zhangxuwei@wxchina.com">zhangxuwei</a>
* @date 2018年4月26日
* @version 1.0.0
*/
@Service
public class LiveStreamRoomServiceImpl extends BaseService implements LiveStreamRoomService {
	private static final Logger logger = LoggerFactory.getLogger(LiveStreamRoomServiceImpl.class);
	private static final String LIST_URL ="/live/list.json";   // "/live/list.json";
	@Override
	public RestEntity<LiveStreamRoom> getLiveStreamRoom(String liveStreamName,String gameType,String status,Long offset,Integer limit) {
		try {
			// 增加header 请求头
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("TAP-CLIENT-TYPE", "0"); // 0web前端 （2：安卓 3：iOS）
			requestHeaders.add("TAP-CLIENT-VERSION", "1"); // 客户端版本
			requestHeaders.add("Content-Type",  "application/x-www-form-urlencoded");
			Map<String, String> parameters = new HashMap<>();
			if(StringUtils.isNotBlank(liveStreamName)){
				parameters.put("liveStreamName", liveStreamName);
			}
			if(StringUtils.isNotBlank(gameType)){
				parameters.put("gameType", gameType);
			}
			if(StringUtils.isNotBlank(status)){
				parameters.put("status", status);
			}
			parameters.put("limit", String.valueOf(limit));
			parameters.put("offset", String.valueOf(offset));
	        
			String str=restTemplate.getForObject(createUrl(LIST_URL, parameters),String.class);
			RestEntity<LiveStreamRoom> deserialize = JsonUtil.deserialize(str, RestEntity.class);
			return deserialize;
		} catch (RestClientException e) {
			logger.error("getLiveStreamRoom fail:",e);
			return null;
		} catch (Exception e) {
			logger.error("getLiveStreamRoom fail:",e);
			return null;
		}
	}
	
	
	
	public static void main(String[] args) {
		String encoding = "UTF-8";  
        File file = new File("D:/eee.txt");  
        Long filelength = file.length();  
        byte[] filecontent = new byte[filelength.intValue()];  
        try {  
            FileInputStream in = new FileInputStream(file);  
            in.read(filecontent);  
            in.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        try {  
          String str = new String(filecontent, encoding);  
          RestEntity<LiveStreamRoom> deserialize = JsonUtil.deserialize(str, RestEntity.class);
          System.out.println(deserialize.getT().getResult().size());
        } catch (UnsupportedEncodingException e) {  
            System.err.println("The OS does not support " + encoding);  
            e.printStackTrace();  
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}

}
