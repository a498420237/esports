package cn.esports.service;

import com.alibaba.fastjson.JSONObject;

import cn.esports.utils.SessionUtil;

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

import java.util.Map;

@Component
public class MyMatchService extends BaseService {

	private static final Logger logger = LoggerFactory.getLogger(MyMatchService.class);
	private static final String list_url = "/match/MyMatchList.json";
	private static final String old_list_url = "/troops/findTroopsMatchHistoryList.json";
	private static final String info_url = "match/findMatchInfoByApp.json";

	public JSONObject getMyMatchList(String token,Map<String, String> uriVariables) {
		try {
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("TAP-CLIENT-TYPE", "0"); // 0web前端 （2：安卓 3：iOS）
			requestHeaders.add("TAP-CLIENT-VERSION", "0.001"); // 客户端版本
			requestHeaders.add("TAP-CLIENT-TOKEN", SessionUtil.getCurToken()); // 客户端版本
			requestHeaders.add("Content-Type", "application/x-www-form-urlencoded");
			MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
			uriVariables.put("userId",String.valueOf(SessionUtil.getCurUid()));
			HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity<>(postParameters, requestHeaders);
			return restTemplate.postForObject(createUrl(list_url,uriVariables),r, JSONObject.class);
		} catch (RestClientException e) {
			logger.error("call the getMyMatchList list from rest api occurred error,cause by:",e);
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("code", 100);
			jsonObject.put("msg", "调用远程接口发生错误，请检联系管理员");
			return  jsonObject;
		}
	}
	public JSONObject getMyMatchOldList(String token,Map<String, String> uriVariables) {
		try {
		
			return restTemplate.getForObject(createUrl(old_list_url,uriVariables), JSONObject.class);
		} catch (RestClientException e) {
			logger.error("call the getMyMatchOldList list from rest api occurred error,cause by:",e);
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("code", 100);
			jsonObject.put("msg", "调用远程接口发生错误，请检联系管理员");
			return  jsonObject;
		}
	}
	
	public JSONObject getMyMatchInfo(String token,Map<String, String> uriVariables) {
		try {
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("TAP-CLIENT-TYPE", "0"); // 0web前端 （2：安卓 3：iOS）
			requestHeaders.add("TAP-CLIENT-VERSION", "0.001"); // 客户端版本
			requestHeaders.add("TAP-CLIENT-TOKEN", baseConfig.getToken()); // 客户端版本
			requestHeaders.add("Content-Type", "application/x-www-form-urlencoded");
			MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
			/*for (Map.Entry<String, String> entry : uriVariables.entrySet()) {
				postParameters.add(entry.getKey(), entry.getValue());
			}*/
			HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity<>(postParameters, requestHeaders);
			ResponseEntity<String> resp = restTemplate.exchange(createUrl(info_url,uriVariables) ,HttpMethod.GET,r, String.class);

			return JSONObject.parseObject(resp.getBody());
		} catch (RestClientException e) {
			logger.error("call the getMyMatchInfo list from rest api occurred error,cause by:",e);
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("code", 100);
			jsonObject.put("msg", "调用远程接口发生错误，请检联系管理员");
			return  jsonObject;
		}
	}
}
