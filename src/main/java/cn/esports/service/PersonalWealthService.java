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
public class PersonalWealthService extends BaseService {

	private static final Logger logger = LoggerFactory.getLogger(PersonalWealthService.class);

	public JSONObject getPersonalWealthInfo(String token,Map<String, String> uriVariables) {
		try {
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("TAP-CLIENT-TYPE", "0"); // 0web前端 （2：安卓 3：iOS）
			requestHeaders.add("TAP-CLIENT-VERSION", "0.001"); // 客户端版本
			requestHeaders.add("TAP-CLIENT-TOKEN", SessionUtil.getCurToken()); // 客户端版本
			requestHeaders.add("Content-Type", "application/x-www-form-urlencoded");
			MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
			HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity<>(postParameters, requestHeaders);
			 ResponseEntity<String> resp = restTemplate.exchange(createUrl("/api/user/getPersonalWealthInfo.json",uriVariables) ,HttpMethod.GET,r, String.class);
			 return JSONObject.parseObject(resp.getBody());
		} catch (RestClientException e) {
			logger.error("call the getPersonalWealthInfo list from rest api occurred error,cause by:",e);
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("code", 100);
			jsonObject.put("msg", "调用远程接口发生错误，请检联系管理员");
			return  jsonObject;
		}
	}
}
