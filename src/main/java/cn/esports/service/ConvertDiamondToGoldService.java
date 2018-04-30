package cn.esports.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;

import com.alibaba.fastjson.JSONObject;

import cn.esports.utils.SessionUtil;


@Component
public class ConvertDiamondToGoldService extends BaseService {

	private static final Logger logger = LoggerFactory.getLogger(PersonalWealthService.class);

	@Autowired
	public UserService userService;
	
	public JSONObject getConvertDiamondToGoldList(String token,Map<String, String> uriVariables) {
		try {
			String tokenStr=SessionUtil.getCurToken();
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("TAP-CLIENT-TYPE", "0"); // 0web前端 （2：安卓 3：iOS）
			requestHeaders.add("TAP-CLIENT-VERSION", "0.001"); // 客户端版本
			requestHeaders.add("TAP-CLIENT-TOKEN",tokenStr); // 客户端版本
			requestHeaders.add("Content-Type", "application/x-www-form-urlencoded");

			
			HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(getPostParameters(uriVariables), requestHeaders);
			
			JSONObject resp= restTemplate.postForObject(createUrl("/api/user/convertDiamondToGold.json", null), httpEntity, JSONObject.class);
			if(resp.get("code").toString().equals("200")){
				String load=userService.getUserInfo(tokenStr);
				SessionUtil.setCurUser(load,tokenStr);
			}
			return resp;
		} catch (RestClientException e) {
			logger.error("call the getPersonalWealthInfo list from rest api occurred error,cause by:",e);
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("code", 100);
			jsonObject.put("msg", "调用远程接口发生错误，请检联系管理员");
			return  jsonObject;
		}
	}
}
