package cn.esports.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.esports.entity.Troops;

/**
 * Competition赛制>Troops站队
* <p>Title: CompetitionTroopsService</p>  
* <p>Description: </p>  
* @author zhimin.hu  
* @date 2018年4月24日
 */

@Component
public class CompetitionTroopsService extends BaseService {
	private static Logger logger = LoggerFactory.getLogger(CompetitionTroopsService.class);
	/**
	 * 站队列表数据
	 * @return
	 */
	public  JSONObject getTroopsList(Map<String,String> uriVariables) {
		try {
			return restTemplate.getForObject(createUrl("/troops/findTroopsListByApp.json", uriVariables),JSONObject.class);
		} catch (RestClientException e) {
			logger.error("get the message list from rest api occurred error,cause by:",e);
			return null;
		}
	}
	
	/**
	 * 个人用户战队数据
	 * @return
	 */
	public  JSONObject getUserWithoutTroopsList(Map<String,String> uriVariables) {
		try {
			return restTemplate.getForObject(createUrl("/troops/userApplyInvitationList.json", uriVariables),JSONObject.class);
		} catch (RestClientException e) {
			logger.error("get the message list from rest api occurred error,cause by:",e);
			return null;
		}
	}
	
	
}
