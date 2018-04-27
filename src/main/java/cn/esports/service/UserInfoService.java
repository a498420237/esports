package cn.esports.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.alibaba.fastjson.JSON;
import cn.esports.entity.UserInfo;
import cn.esports.entity.UserLogin;
import cn.esports.enums.SendType;

/**
 * 
 * <p>
 * Title: UserIndexService
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author zhimin.hu
 * @date 2018年4月25日
 */
@Component
public class UserInfoService extends BaseService {

	/**
	 * 效验手机和验证码是否匹配
	 * 
	 * @param phone
	 * @param code
	 * @return
	 */
	public UserLogin GetToken(String phone, String code) {
		UserLogin token = null  ;
		try {
			//String url = baseConfig.getHttpUrl() + "/api/msg/verifySecurityCode";
			String url = baseConfig.getHttpUrl() + "/api/index/login"; //登陆注册
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("TAP-CLIENT-TYPE", "0"); // 0web前端 （2：安卓 3：iOS）
			requestHeaders.add("TAP-CLIENT-VERSION", "0.0.1"); // 客户端版本
			requestHeaders.add("Content-Type", "application/x-www-form-urlencoded");
			MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<>();
			postParameters.add("mobile", phone);
			//postParameters.add("type", TYPEENUM.LOGIN.getIndex());
			postParameters.add("code", code);
			HttpEntity<MultiValueMap<String, String>> r = new HttpEntity<>(postParameters, requestHeaders);
			//String jsString=restTemplate.postForObject(url, r, String.class);
			//UserLogin userLogin=JSON.parseObject(jsString,UserLogin.class);
			//UserLogin userLogin = restTemplate.postForObject(url, postParameters, UserLogin.class);
			ResponseEntity<String> resp = restTemplate.exchange(url ,HttpMethod.POST,r, String.class);
	        //获取返回的header
			UserLogin userLogin=JSON.parseObject(resp.getBody(),UserLogin.class);
	        List<String> cookies = resp.getHeaders().get("Set-Cookie");
	        if(cookies.size()>0) {
	        	for (int i = 0; i < cookies.size(); i++) {
					System.out.println("cookie:"+cookies.get(i));
					
					if(cookies.get(i).startsWith("rememberTap_token")) {
						Pattern pattern = Pattern.compile("rememberTap_token=(.*);");
				        Matcher matcher = pattern.matcher(cookies.get(i));
				        if(matcher.find()){
				            System.out.println(matcher.group(1));
				            userLogin.setToken(matcher.group(1));
				        }
		        	}
					if(cookies.get(i).startsWith("userName")) {
						Pattern pattern = Pattern.compile("userName=(.*);");
				        Matcher matcher = pattern.matcher(cookies.get(i));
				        if(matcher.find()){
				            System.out.println(matcher.group(1));
				            userLogin.setUserName(matcher.group(1));
				        }
		        	}
					if(cookies.get(i).startsWith("uid")) {
						Pattern pattern = Pattern.compile("uid=(.*);");
				        Matcher matcher = pattern.matcher(cookies.get(i));
				        if(matcher.find()){
				            System.out.println(matcher.group(1));
				            userLogin.setUid(Integer.valueOf(matcher.group(1)));
				        }
		        	}
				}
	        }
			token=userLogin;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;
	}

	/**
	 * 发送手机验证码
	 * 
	 * @param phone
	 * @param code
	 * @return
	 */
	public UserLogin SendSMS(String mobile, SendType typeEnum) {
		UserLogin token = null ;
		try {
			String url = baseConfig.getHttpUrl() + "/api/msg/sendMobileCode.json";
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("TAP-CLIENT-TYPE", "0"); // 0web前端 （2：安卓 3：iOS）
			requestHeaders.add("TAP-CLIENT-VERSION", "0.001"); // 客户端版本
			//requestHeaders.add("Content-Type", "application/x-www-form-urlencoded");
			MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
			postParameters.add("mobile", mobile);
			postParameters.add("type", typeEnum.LOGIN.getIndex());
			HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity<>(postParameters, requestHeaders);
			UserLogin models = restTemplate.postForObject(url, r, UserLogin.class);
			token=models;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;
	}

	/**
	 * 获取用户信息
	 * 
	 * @return
	 */
	public UserInfo.TBean GetUserInfo(String token) {
		UserInfo.TBean tBean = new UserInfo.TBean();
		try {
			String url = baseConfig.getHttpUrl() + "/api/user/getUserInfo";
			// 增加header 请求头
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("TAP-CLIENT-TYPE", "0"); // 0web前端 （2：安卓 3：iOS）
			requestHeaders.add("TAP-CLIENT-VERSION", "0.001"); // 客户端版本
			requestHeaders.add("TAP-CLIENT-TOKEN", token); // 客户端版本
			requestHeaders.add("Content-Type", "application/x-www-form-urlencoded");
			MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
			//postParameters.add("token", mobile);
			//postParameters.add("type", typeEnum.name());
			HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity<>(postParameters, requestHeaders);
			UserInfo models = restTemplate.postForObject(url, r, UserInfo.class);
			tBean=models.getT();
			
			//ResponseEntity<String> resp = restTemplate.exchange(url ,HttpMethod.POST,r, String.class);
	        //获取返回的header
	        //List<String> val = resp.getHeaders().get("Set-Cookie");
	        
	        //String body = resp.getBody();
	        //String bString="";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tBean;
	}

}
