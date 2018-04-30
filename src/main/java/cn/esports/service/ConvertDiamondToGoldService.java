package cn.esports.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;

import com.alibaba.fastjson.JSONObject;


@Component
public class ConvertDiamondToGoldService extends BaseService {

	private static final Logger logger = LoggerFactory.getLogger(PersonalWealthService.class);

	public JSONObject getConvertDiamondToGoldList(String token,Map<String, String> uriVariables) {
		try {
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("TAP-CLIENT-TYPE", "0"); // 0web前端 （2：安卓 3：iOS）
			requestHeaders.add("TAP-CLIENT-VERSION", "0.001"); // 客户端版本
			requestHeaders.add("TAP-CLIENT-TOKEN", baseConfig.getToken()); // 客户端版本
			requestHeaders.add("Content-Type", "application/x-www-form-urlencoded");

			
			HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(getPostParameters(uriVariables), requestHeaders);
			
			return restTemplate.postForObject(createUrl("/api/user/convertDiamondToGold.json", null), httpEntity, JSONObject.class);
			
		} catch (RestClientException e) {
			logger.error("call the getPersonalWealthInfo list from rest api occurred error,cause by:",e);
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("code", 100);
			jsonObject.put("msg", "调用远程接口发生错误，请检联系管理员");
			return  jsonObject;
		}
	}
}
