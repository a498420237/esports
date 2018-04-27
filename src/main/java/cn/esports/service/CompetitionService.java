package cn.esports.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * 赛事模块 业务层
 * @author liukunrui
 */
@Component
public class CompetitionService extends BaseService {
	
	private static final Logger logger = LoggerFactory.getLogger(CompetitionService.class);
	
	private static final String LIST_URL = "/match/findMatchListByApp.json";
	private static final String INFO_URL = "/match/findMatchInfoByApp.json";
	
	/**
	 * 获取赛事列表
	 * @param uriVariables
	 * @return
	 */
	public JSONObject getCompetitions(Map<String, String> uriVariables) {
		try {
			return restTemplate.getForObject(createUrl(LIST_URL, uriVariables),JSONObject.class);
		} catch (RestClientException e) {
			logger.error("call the forecast list from rest api occurred error,cause by:",e);
			return null;
		}
	}
	
	/**
	 * 获取赛事详情
	 * @param matchId
	 * @return
	 */
	public JSONObject getCompetitionInfo(Long matchId) {
		try {
			Map<String, String> uriVariables = new HashMap<String, String>();
			uriVariables.put("matchId", String.valueOf(matchId));
			return restTemplate.getForObject(createUrl(INFO_URL, uriVariables),JSONObject.class);
		} catch (RestClientException e) {
			logger.error("call the forecast list from rest api occurred error,cause by:",e);
			return null;
		}
	}
}
