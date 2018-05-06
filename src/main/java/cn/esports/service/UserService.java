package cn.esports.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import cn.esports.entity.SimpleUser;
import cn.esports.entity.UserInfo;
import cn.esports.enums.SendType;
import cn.esports.utils.Constants;
import cn.esports.utils.SessionUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

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
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("code", 100);
			jsonObject.put("msg", "调用远程接口发生错误，请检联系管理员");
			return  jsonObject;
		}
	}

	/**
	 * 创建战队--短信验证码
	 * @param mobile
	 * @return
	 */
	public JSONObject sendMobileCodeByCreatTeam(String mobile){
		try {
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("TAP-CLIENT-TYPE", "0"); // 0web前端 （2：安卓 3：iOS）
			requestHeaders.add("TAP-CLIENT-VERSION", "0.001"); // 客户端版本
			//requestHeaders.add("Content-Type", "application/x-www-form-urlencoded");

			MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
			postParameters.add("mobile", mobile);
			postParameters.add("type", SendType.CREATETROOP.getIndex());
			HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(postParameters, requestHeaders);

			return restTemplate.postForObject(createUrl("/api/msg/sendMobileCode.json", null), httpEntity, JSONObject.class);
		} catch (RestClientException e) {
			logger.error("send mobile code to rest api occurred error,cause by:",e);
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("code", 100);
			jsonObject.put("msg", "调用远程接口发生错误，请检联系管理员");
			return  jsonObject;
		}
	}

	/**
	 * 队长转移申请--短信验证码
	 * @param mobile
	 * @return
	 */
	public JSONObject sendMobileCodeByZydz(String mobile){
		try {
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("TAP-CLIENT-TYPE", "0"); // 0web前端 （2：安卓 3：iOS）
			requestHeaders.add("TAP-CLIENT-VERSION", "0.001"); // 客户端版本
			//requestHeaders.add("Content-Type", "application/x-www-form-urlencoded");

			MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
			postParameters.add("mobile", mobile);
			postParameters.add("type", SendType.TRANSFERAPTAIN.getIndex());
			HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(postParameters, requestHeaders);

			return restTemplate.postForObject(createUrl("/api/msg/sendMobileCode.json", null), httpEntity, JSONObject.class);
		} catch (RestClientException e) {
			logger.error("send mobile code to rest api occurred error,cause by:",e);
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("code", 100);
			jsonObject.put("msg", "调用远程接口发生错误，请检联系管理员");
			return  jsonObject;
		}
	}
	/**
	 * 发送邮箱验证码
	 * @param email
	 * @param type
	 * @return
	 */
	public JSONObject sendEmailCode(String email,String type){
		try {
			//发送类型（绑定邮箱：bindEmail 更换邮箱：resetBindEamil 更换手机号：resetBindMobile）
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("TAP-CLIENT-TYPE", "0"); // 0web前端 （2：安卓 3：iOS）
			requestHeaders.add("TAP-CLIENT-VERSION", "0.001"); // 客户端版本
			//requestHeaders.add("Content-Type", "application/x-www-form-urlencoded");
			
			MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
			postParameters.add("email", email);
			postParameters.add("type", type);
			HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(postParameters, requestHeaders);
			
			return restTemplate.postForObject(createUrl("api/msg/sendEmailCode.json", null), httpEntity, JSONObject.class);
		} catch (RestClientException e) {
			logger.error("send mobile code to rest api occurred error,cause by:",e);
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("code", 100);
			jsonObject.put("msg", "调用远程接口发生错误，请检联系管理员");
			return  jsonObject;
		}
	}
	
	/**
	 * 效验手机号和验证码是否正确
	 * @param mobile
	 * @param code
	 * @return
	 */
	public String getToken(String mobile,String code){
		try {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("TAP-CLIENT-TYPE", "0"); // 0web前端 （2：安卓 3：iOS）
		requestHeaders.add("TAP-CLIENT-VERSION", "0.0.1"); // 客户端版本
		//requestHeaders.add("Content-Type", "application/x-www-form-urlencoded");
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
	        	}
			}
		}
		SessionUtil.setLoginCode(mobile, resp.getBody());
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

	/**
	 * 修改用户信息
	 * @param mobile
	 * @return
	 */
	public JSONObject saveUserInfo(Map<String, String> uriVariables,byte[] inputStream){
		try {
			if(inputStream!=null) {
			 Configuration cfg = new Configuration(Zone.zone0());//构造一个带指定Zone对象的配置类
	         UploadManager uploadManager = new UploadManager(cfg);
	         Auth auth = Auth.create(qiniuConfig.getAccessKey(), qiniuConfig.getSecretKey());
	         String upToken = auth.uploadToken(qiniuConfig.getBucket());
	         String key = null;
	         try {
	        	
	        	 key=uriVariables.get("avatar");
	             byte[] bytes = inputStream;
	             ByteArrayInputStream byteInputStream=new ByteArrayInputStream(bytes);
	             Response response = uploadManager.put(byteInputStream,key,upToken,null, null);
	             //解析上传成功的结果
	             DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
			} catch (Exception e) {
				// TODO: handle exception
			}}
			
			HttpHeaders requestHeaders = new HttpHeaders();
			String tokenStr=SessionUtil.getCurToken();
			requestHeaders.add("TAP-CLIENT-TYPE", "0"); // 0web前端 （2：安卓 3：iOS）
			requestHeaders.add("TAP-CLIENT-VERSION", "0.001"); // 客户端版本
			requestHeaders.add("TAP-CLIENT-TOKEN",tokenStr); // 客户端版本
			requestHeaders.add("Content-Type", "application/x-www-form-urlencoded");
			
			MultiValueMap<String, Object> postParameters = getPostParameters(uriVariables);
			HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(postParameters, requestHeaders);
			JSONObject resp = restTemplate.postForObject(createUrl("/api/user/completeUserInfo.json", null), httpEntity, JSONObject.class);
			if(resp.get("code").toString().equals("200")){
				String load= getUserInfo(tokenStr);
				SessionUtil.setCurUser(load,tokenStr);
			}
			return resp;
		} catch (RestClientException e) {
			logger.error("saveUserInfo to rest api occurred error,cause by:",e);
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("code", 100);
			jsonObject.put("msg", "调用远程接口发生错误，请检联系管理员");
			return  jsonObject;
		}
	}
	/**
	 * 修改与绑定手机号码
	 * @param mobile 操作类型（1：绑定 2：更换）
	 * @return
	 */
	public JSONObject BindMobile(Map<String, String> uriVariables){
		try {
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("TAP-CLIENT-TYPE", "0"); // 0web前端 （2：安卓 3：iOS）
			requestHeaders.add("TAP-CLIENT-VERSION", "0.001"); // 客户端版本
			requestHeaders.add("TAP-CLIENT-TOKEN",SessionUtil.getCurToken()); // 客户端版本
			requestHeaders.add("Content-Type", "application/x-www-form-urlencoded");
			
			MultiValueMap<String, Object> postParameters = getPostParameters(uriVariables);
			HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(postParameters, requestHeaders);
			return restTemplate.postForObject(createUrl("/api/user/bindMobile.json", null), httpEntity, JSONObject.class);
		} catch (RestClientException e) {
			logger.error("saveUserInfo to rest api occurred error,cause by:",e);
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("code", 100);
			jsonObject.put("msg", "调用远程接口发生错误，请检联系管理员");
			return  jsonObject;
		}
	}
	/**
	 * 修改与绑定邮箱
	 * @param mobile 操作类型（1：绑定 2：更换）
	 * @return
	 */
	public JSONObject BindEmail(Map<String, String> uriVariables){
		try {
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("TAP-CLIENT-TYPE", "0"); // 0web前端 （2：安卓 3：iOS）
			requestHeaders.add("TAP-CLIENT-VERSION", "0.001"); // 客户端版本
			requestHeaders.add("TAP-CLIENT-TOKEN",SessionUtil.getCurToken()); // 客户端版本
			requestHeaders.add("Content-Type", "application/x-www-form-urlencoded");
			
			MultiValueMap<String, Object> postParameters = getPostParameters(uriVariables);
			HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(postParameters, requestHeaders);
			return restTemplate.postForObject(createUrl("/api/user/bindEmail.json", null), httpEntity, JSONObject.class);
		} catch (RestClientException e) {
			logger.error("saveUserInfo to rest api occurred error,cause by:",e);
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("code", 100);
			jsonObject.put("msg", "调用远程接口发生错误，请检联系管理员");
			return  jsonObject;
		}
	}
	
	/**
	 * 添加用户图片
	 * @param mobile
	 * @return
	 */
	public JSONObject addUserPicture(List<MultipartFile> files){
		JSONObject jsonObject=new JSONObject();
		try {
			
			int allCount=files.size();
			int okCount=0; //成功数量
			int failCount=0; //失败数量
	         Configuration cfg = new Configuration(Zone.zone0());//构造一个带指定Zone对象的配置类
	         UploadManager uploadManager = new UploadManager(cfg);
	         Auth auth = Auth.create(qiniuConfig.getAccessKey(), qiniuConfig.getSecretKey());
	         String upToken = auth.uploadToken(qiniuConfig.getBucket());
	         String key = null;
	         String mobile="";
	         MultipartFile file = null; 
	         String ErrorMsg="";
	         for (int i = 0; i < files.size(); ++i) {      
	             file = files.get(i);      
	             if (!file.isEmpty()) {      
	                 try {  
	                	 mobile=SessionUtil.getCurMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
	                	 key=mobile+"_"+ System.currentTimeMillis()+"_"+file.getOriginalFilename();
	                     byte[] bytes = file.getBytes();
	                     ByteArrayInputStream byteInputStream=new ByteArrayInputStream(bytes);
	                     Response response = uploadManager.put(byteInputStream,key,upToken,null, null);
	                     //解析上传成功的结果
	                     DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
	                     JSONObject resp= addPictures(key);
	                     if("200".equals(resp.get("code").toString())) {
	                    	 okCount++;
	                     }else {
	                    	 failCount++;
	                    	 ErrorMsg=resp.get("msg").toString();
	                     }
	                    
	                 }catch (Exception e) {
						// TODO: handle exception
	                	 failCount++;
					}
	             }
	         }
	        jsonObject.put("code", 200);
			jsonObject.put("msg", "总上传数量："+allCount+"张, 其中成功："+okCount +"张 , 失败："+failCount+"张 "+ErrorMsg);
		} catch (RestClientException e) {
			logger.error("saveUserInfo to rest api occurred error,cause by:",e);
			jsonObject.put("code", 100);
			jsonObject.put("msg", "调用远程接口发生错误，请检联系管理员");
		}
		return  jsonObject;
	}
	
	public JSONObject addPictures(String fileName) {
		JSONObject jsonObject=new JSONObject();
		try {
			
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("TAP-CLIENT-TYPE", "0"); // 0web前端 （2：安卓 3：iOS）
		requestHeaders.add("TAP-CLIENT-VERSION", "0.001"); // 客户端版本
		requestHeaders.add("TAP-CLIENT-TOKEN",SessionUtil.getCurToken()); // 客户端版本
		requestHeaders.add("Content-Type", "application/x-www-form-urlencoded");
		Map<String, String> uriVariables =new HashMap<String, String>();
		uriVariables.put("url", fileName);
		MultiValueMap<String, Object> postParameters = getPostParameters(uriVariables);
		HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(postParameters, requestHeaders);
		JSONObject resp= restTemplate.postForObject(createUrl("/api/user/userPicture.json", null), httpEntity, JSONObject.class);
		if(resp.get("code").toString().equals("200")){
			String load= getUserInfo(SessionUtil.getCurToken());
			SessionUtil.setCurUser(load,SessionUtil.getCurToken());
		}
		return resp;
		} catch (RestClientException e) {
			logger.error("saveUserInfo to rest api occurred error,cause by:",e);
			jsonObject.put("code", 100);
			jsonObject.put("msg", "调用远程接口发生错误，请检联系管理员");
		}
		return  jsonObject;
	}
	/**
	 * 删除用户图片
	 * @param mobile
	 * @return
	 */
	public JSONObject delUserPicture(Map<String, String> uriVariables){
		try {
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("TAP-CLIENT-TYPE", "0"); // 0web前端 （2：安卓 3：iOS）
			requestHeaders.add("TAP-CLIENT-VERSION", "0.001"); // 客户端版本
			requestHeaders.add("TAP-CLIENT-TOKEN",SessionUtil.getCurToken()); // 客户端版本
			requestHeaders.add("Content-Type", "application/x-www-form-urlencoded");
			
			MultiValueMap<String, Object> postParameters = getPostParameters(uriVariables);
			HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(postParameters, requestHeaders);
			JSONObject resp=  restTemplate.postForObject(createUrl("api/user/deleteUserPicture.json", null), httpEntity, JSONObject.class);
			if(resp.get("code").toString().equals("200")){
				String load= getUserInfo(SessionUtil.getCurToken());
				SessionUtil.setCurUser(load,SessionUtil.getCurToken());
			}
			return resp;
		} catch (RestClientException e) {
			logger.error("saveUserInfo to rest api occurred error,cause by:",e);
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("code", 100);
			jsonObject.put("msg", "调用远程接口发生错误，请检联系管理员");
			return  jsonObject;
		}
	}
	
	
	/**
	 * 获取游戏类型
	 * @return
	 */
	public JSONObject getGameInfo(Map<String, String> uriVariables){
		try {
			String url = baseConfig.getHttpUrl() + "/api/user/getUserInfo";
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("TAP-CLIENT-TYPE", "0"); // 0web前端 （2：安卓 3：iOS）
			requestHeaders.add("TAP-CLIENT-VERSION", "0.001"); // 客户端版本
	        HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
	        ResponseEntity<String> response = restTemplate.exchange(createUrl("api/gameAccount/getGameInfo.json", uriVariables), HttpMethod.GET, requestEntity, String.class);
	        String uString=  response.getBody();
	        return JSONObject.parseObject(uString);
		} catch (RestClientException e) {
			logger.error("getGameInfo to rest api occurred error,cause by:",e);
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("code", 100);
			jsonObject.put("msg", "调用远程接口发生错误，请检联系管理员");
			return  jsonObject;
		}
	}
	
	
	
	/**
	 * 游戏类型获取区服
	 * @return
	 */
	public JSONObject getGameArea(Map<String, String> uriVariables){
		try {
			String url = baseConfig.getHttpUrl() + "/api/user/getUserInfo";
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("TAP-CLIENT-TYPE", "0"); // 0web前端 （2：安卓 3：iOS）
			requestHeaders.add("TAP-CLIENT-VERSION", "0.001"); // 客户端版本
			requestHeaders.add("TAP-CLIENT-TOKEN", SessionUtil.getCurToken()); // 客户端版本
			requestHeaders.add("Content-Type", "application/x-www-form-urlencoded");
	        HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
	        ResponseEntity<String> response = restTemplate.exchange(createUrl("api/gameAccount/getGameArea.json", uriVariables), HttpMethod.GET, requestEntity, String.class);
	        String uString=  response.getBody();
	        return JSONObject.parseObject(uString);
		} catch (RestClientException e) {
			logger.error("saveUserInfo to rest api occurred error,cause by:",e);
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("code", 100);
			jsonObject.put("msg", "调用远程接口发生错误，请检联系管理员");
			return  jsonObject;
		}
	}
	public JSONObject getGameRanks(Map<String, String> uriVariables){
		try {
			String url = baseConfig.getHttpUrl() + "/api/user/getUserInfo";
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("TAP-CLIENT-TYPE", "0"); // 0web前端 （2：安卓 3：iOS）
			requestHeaders.add("TAP-CLIENT-VERSION", "0.001"); // 客户端版本
			requestHeaders.add("TAP-CLIENT-TOKEN", SessionUtil.getCurToken()); // 客户端版本
			requestHeaders.add("Content-Type", "application/x-www-form-urlencoded");
	        HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
	        ResponseEntity<String> response = restTemplate.exchange(createUrl("api/gameAccount/getGameRanks.json", uriVariables), HttpMethod.GET, requestEntity, String.class);
	        String uString=  response.getBody();
	        return JSONObject.parseObject(uString);
		} catch (RestClientException e) {
			logger.error("getGameRanks to rest api occurred error,cause by:",e);
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("code", 100);
			jsonObject.put("msg", "调用远程接口发生错误，请检联系管理员");
			return  jsonObject;
		}
	}
	
	
	/**
	 * 获取绑定账号
	 * @return
	 */
	public JSONObject getUserBingGameAccess(Map<String, String> uriVariables){
		try {
			String url = baseConfig.getHttpUrl() + "/api/user/getUserInfo";
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("TAP-CLIENT-TYPE", "0"); // 0web前端 （2：安卓 3：iOS）
			requestHeaders.add("TAP-CLIENT-VERSION", "0.001"); // 客户端版本
			requestHeaders.add("TAP-CLIENT-TOKEN", SessionUtil.getCurToken()); // 客户端版本
			requestHeaders.add("Content-Type", "application/x-www-form-urlencoded");
	        HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
	        ResponseEntity<String> response = restTemplate.exchange(createUrl("api/gameAccount/getUserBindGame.json", uriVariables), HttpMethod.GET, requestEntity, String.class);
	        String uString=  response.getBody();
	        return JSONObject.parseObject(uString);
		} catch (RestClientException e) {
			logger.error("saveUserInfo to rest api occurred error,cause by:",e);
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("code", 100);
			jsonObject.put("msg", "调用远程接口发生错误，请检联系管理员");
			return  jsonObject;
		}
	}
	/**
	 * 用户绑定绑定游戏账号
	 * @param mobile
	 * @return
	 */
	public JSONObject UserBindgameAccess(Map<String, String> uriVariables){
		try {
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("TAP-CLIENT-TYPE", "0"); // 0web前端 （2：安卓 3：iOS）
			requestHeaders.add("TAP-CLIENT-VERSION", "0.001"); // 客户端版本
			requestHeaders.add("TAP-CLIENT-TOKEN",SessionUtil.getCurToken()); // 客户端版本
			requestHeaders.add("Content-Type", "application/x-www-form-urlencoded");
			
			MultiValueMap<String, Object> postParameters = getPostParameters(uriVariables);
			HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(postParameters, requestHeaders);
			JSONObject resp= restTemplate.postForObject(createUrl("api/gameAccount/bind.json", null), httpEntity, JSONObject.class);
			
			return resp;
		} catch (RestClientException e) {
			logger.error("UserBindgameAccess to rest api occurred error,cause by:",e);
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("code", 100);
			jsonObject.put("msg", "调用远程接口发生错误，请检联系管理员");
			return  jsonObject;
		}
	}
	
}
