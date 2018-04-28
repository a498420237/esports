package cn.esports.service;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import cn.esports.entity.SimpleUser;
import cn.esports.entity.UserInfo;
import cn.esports.enums.SendType;

import com.alibaba.fastjson.JSONObject;

@Component
public class UserService extends BaseService{
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	/**
	 * 发送手机验证码
	 * @param mobile
	 * @return
	 */
	public JSONObject sendMobileCode(String mobile){
		try {
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("TAP-CLIENT-TYPE", "0"); // 0web前端 （2：安卓 3：iOS）
			requestHeaders.add("TAP-CLIENT-VERSION", "0.001"); // 客户端版本
			//requestHeaders.add("Content-Type", "application/x-www-form-urlencoded");
			
			MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
			postParameters.add("mobile", mobile);
			postParameters.add("type", SendType.LOGIN.getIndex());
			HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(postParameters, requestHeaders);
			
			return restTemplate.postForObject(createUrl("/api/msg/sendMobileCode.json", null), httpEntity, JSONObject.class);
		} catch (RestClientException e) {
			logger.error("send mobile code to rest api occurred error,cause by:",e);
			return null;
		}
	}
	
	public String getToken(String mobile,String code){
		try {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("TAP-CLIENT-TYPE", "0"); // 0web前端 （2：安卓 3：iOS）
		requestHeaders.add("TAP-CLIENT-VERSION", "0.0.1"); // 客户端版本
		requestHeaders.add("Content-Type", "application/x-www-form-urlencoded");
		MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<>();
		postParameters.add("mobile", mobile);
		postParameters.add("code", code);
		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(postParameters, requestHeaders);
		ResponseEntity<JSONObject> resp = restTemplate.exchange(createUrl("/api/index/login", null) ,HttpMethod.POST,httpEntity, JSONObject.class);
		if(resp.getBody().get("code").toString().equals("200")){
			List<String> cookies = resp.getHeaders().get("Set-Cookie");
			for(String cookie:cookies){
				if(cookie.startsWith("rememberTap_token")) {
					resp.getBody().put("token", cookie.replace("rememberTap_token=", ""));
					Pattern pattern = Pattern.compile("rememberTap_token=(.*);");
			        Matcher matcher = pattern.matcher(cookie);
			        if(matcher.find()){
			           return  matcher.group(1);
			        }
					//return cookie.replaceAll("rememberTap_token=(.*);", "$1");
	        	}
			}
		}
		} catch (RestClientException e) {
			logger.error("validation Token to rest api occurred error,cause by:",e);
		}
		return "";
	}
	/**
	 * 获取用户信息
	 * 
	 * @return
	 */
	public String  getUserInfo(String token) {
		
		try {
			String url = baseConfig.getHttpUrl() + "/api/user/getUserInfo";
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("TAP-CLIENT-TYPE", "0"); // 0web前端 （2：安卓 3：iOS）
			requestHeaders.add("TAP-CLIENT-VERSION", "0.001"); // 客户端版本
			requestHeaders.add("TAP-CLIENT-TOKEN", token); // 客户端版本
			requestHeaders.add("Content-Type", "application/x-www-form-urlencoded");
	        HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
	        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
	        String uString=  response.getBody();
	        return uString;
		} catch (Exception e) {
			logger.error("getUserInfo Token to rest api occurred error,cause by:",e);
		}
		return "";
	}
}
