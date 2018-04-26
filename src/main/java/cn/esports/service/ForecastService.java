package cn.esports.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import com.alibaba.fastjson.JSONObject;

@Component
public class ForecastService extends BaseService {
	
	private static final Logger logger = LoggerFactory.getLogger(ForecastService.class);

	public JSONObject getForecastList(Map<String, String> uriVariables) {
		try {
			return restTemplate.getForObject(createUrl("/quiz/list.json", uriVariables),JSONObject.class);
		} catch (RestClientException e) {
			logger.error("call the forecast list from rest api occurred error,cause by:",e);
			return null;
		}
	}
	
	/**
	 * ·获取游戏类型接口
	 * @param uriVariables
	 * applySite 应用位置（1：竞猜 2：直播）
	 * @return
	 */
	public JSONObject getGameInfoList(Map<String, String> uriVariables) {
		try {
			return restTemplate.getForObject(createUrl("/quiz/gameInfo/list.json", uriVariables),JSONObject.class);
		} catch (RestClientException e) {
			logger.error("call the getGameInfotList from rest api occurred error,cause by:",e);
			return null;
		}
	}
	
}
