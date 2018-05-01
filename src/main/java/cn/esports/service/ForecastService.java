package cn.esports.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;

import com.alibaba.fastjson.JSONObject;

import cn.esports.utils.SessionUtil;

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
	/**
	 * 获取竞猜详情
	 * @param uriVariables
	 * @return
	 */
	public JSONObject getLotteryInfo(Map<String, String> uriVariables) {
		try {
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("TAP-CLIENT-TYPE", "0"); // 0web前端 （2：安卓 3：iOS）
			requestHeaders.add("TAP-CLIENT-VERSION", "0.001"); // 客户端版本
			//requestHeaders.add("Content-Type", "application/x-www-form-urlencoded");
			MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
			HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity<>(postParameters, requestHeaders);
			 ResponseEntity<String> resp = restTemplate.exchange(createUrl("live/lotteryInfo.json", uriVariables) ,HttpMethod.GET,r, String.class);
			 return JSONObject.parseObject(resp.getBody());
		} catch (RestClientException e) {
			logger.error("call the getGameInfotList from rest api occurred error,cause by:",e);
			return null;
		}
	}
	
	
}
