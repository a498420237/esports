package cn.esports.service;

import cn.esports.utils.SessionUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MyWarteamService extends BaseService {

	private static final Logger logger = LoggerFactory.getLogger(MyWarteamService.class);
	private static final String list_url = "/troops/findTroopsListByUserId.json";
	private static final String all_list_url = "/troops/findTroopsListByApp.json";
	private static final String hh_list_url = "/troops/findTroopsMatchHistoryList.json";
	private static final String member_list_url = "/troops/findTroopsMemberList.json";
	private static final String quit_troops_url = "/troops/quitTroops.json";
	private static final String find_troops_url = "/troops/findTroopsInfoByApp.json";
	private static final String add_team_url = "/troops/applyTroops.json";
	private static final String edit_team_url = "/troops/updateTroops.json";
	private static final String sq_team_url =  "/troops/applyTroopsList.json";
	private static final String operation_team_url =  "/troops/TroopsOperationByCaptain.json";
	private static final String delMember_url =  "/troops/expelTroops.json";
	private static final String zyzd_url="/troops/sendTransferCaptain.json";
	public JSONObject getUserTeamList(String token,Map<String, String> uriVariables){
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
			uriVariables.put("userId", SessionUtil.getCurUid()+"");
			//uriVariables.put("userId", "139");
			HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity<>(postParameters, requestHeaders);
			ResponseEntity<String> resp = restTemplate.exchange(createUrl(list_url,uriVariables) ,HttpMethod.GET,r, String.class);

			return JSONObject.parseObject(resp.getBody());
		} catch (RestClientException e) {
			logger.error("call the getPersonalWealthInfo list from rest api occurred error,cause by:",e);
			return null;
		}
	}

	public JSONObject getAllUserTeamList(String token,Map<String, String> uriVariables){
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
			ResponseEntity<String> resp = restTemplate.exchange(createUrl(all_list_url,uriVariables) ,HttpMethod.GET,r, String.class);

			return JSONObject.parseObject(resp.getBody());
		} catch (RestClientException e) {
			logger.error("call the getPersonalWealthInfo list from rest api occurred error,cause by:",e);
			return null;
		}
	}

	public JSONObject getTeamHonorOrHistoryList(String token,Map<String, String> uriVariables){
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
			ResponseEntity<String> resp = restTemplate.exchange(createUrl(hh_list_url,uriVariables) ,HttpMethod.GET,r, String.class);

			return JSONObject.parseObject(resp.getBody());
		} catch (RestClientException e) {
			logger.error("call the getPersonalWealthInfo list from rest api occurred error,cause by:",e);
			return null;
		}
	}

	public JSONObject getTeamMemberList(String token,Map<String, String> uriVariables){
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
			uriVariables.put("userId", SessionUtil.getCurUid()+"");
			//uriVariables.put("userId", "139");
			HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity<>(postParameters, requestHeaders);
			ResponseEntity<String> resp = restTemplate.exchange(createUrl(member_list_url,uriVariables) ,HttpMethod.GET,r, String.class);

			return JSONObject.parseObject(resp.getBody());
		} catch (RestClientException e) {
			logger.error("call the getPersonalWealthInfo list from rest api occurred error,cause by:",e);
			return null;
		}
	}

	public JSONObject backTeam(String token,Map<String, String> uriVariables){
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
			uriVariables.put("userId", SessionUtil.getCurUid()+"");
			//uriVariables.put("userId", "139");
			HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity<>(postParameters, requestHeaders);
			ResponseEntity<String> resp = restTemplate.exchange(createUrl(quit_troops_url,uriVariables) ,HttpMethod.GET,r, String.class);

			return JSONObject.parseObject(resp.getBody());
		} catch (RestClientException e) {
			logger.error("call the getPersonalWealthInfo list from rest api occurred error,cause by:",e);
			return null;
		}
	}

	public JSONObject teamInfo(String token,Map<String, String> uriVariables){
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
			ResponseEntity<String> resp = restTemplate.exchange(createUrl(find_troops_url,uriVariables) ,HttpMethod.GET,r, String.class);

			return JSONObject.parseObject(resp.getBody());
		} catch (RestClientException e) {
			logger.error("call the getPersonalWealthInfo list from rest api occurred error,cause by:",e);
			return null;
		}
	}

	public JSONObject addTeam(String token,Map<String, String> uriVariables){
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
			uriVariables.put("userId", SessionUtil.getCurUid()+"");
			//uriVariables.put("userId", "132");
			//uriVariables.put("userId", "139");
			HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity<>(postParameters, requestHeaders);
			ResponseEntity<String> resp = restTemplate.exchange(createUrl(add_team_url,uriVariables) ,HttpMethod.GET,r, String.class);

			return JSONObject.parseObject(resp.getBody());
		} catch (RestClientException e) {
			logger.error("call the getPersonalWealthInfo list from rest api occurred error,cause by:",e);
			return null;
		}
	}

	public JSONObject editTeam(String token,Map<String, String> uriVariables){
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
			uriVariables.put("captainId", SessionUtil.getCurUid()+"");
			//uriVariables.put("captainId", "139");
			HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity<>(postParameters, requestHeaders);
			ResponseEntity<String> resp = restTemplate.exchange(createUrl(edit_team_url,uriVariables) ,HttpMethod.GET,r, String.class);

			return JSONObject.parseObject(resp.getBody());
		} catch (RestClientException e) {
			logger.error("call the getPersonalWealthInfo list from rest api occurred error,cause by:",e);
			return null;
		}
	}

	public JSONObject newMemberList(String token,Map<String, String> uriVariables){
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
			uriVariables.put("userId", SessionUtil.getCurUid()+"");
			//uriVariables.put("userId", "139");
			HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity<>(postParameters, requestHeaders);
			ResponseEntity<String> resp = restTemplate.exchange(createUrl(sq_team_url,uriVariables) ,HttpMethod.GET,r, String.class);

			return JSONObject.parseObject(resp.getBody());
		} catch (RestClientException e) {
			logger.error("call the getPersonalWealthInfo list from rest api occurred error,cause by:",e);
			return null;
		}
	}

	public JSONObject operationTeam(String token,Map<String, String> uriVariables){
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
			uriVariables.put("captainId", SessionUtil.getCurUid()+"");
			//uriVariables.put("captainId", "139");
			HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity<>(postParameters, requestHeaders);
			ResponseEntity<String> resp = restTemplate.exchange(createUrl(operation_team_url,uriVariables) ,HttpMethod.GET,r, String.class);

			return JSONObject.parseObject(resp.getBody());
		} catch (RestClientException e) {
			logger.error("call the getPersonalWealthInfo list from rest api occurred error,cause by:",e);
			return null;
		}
	}

	public JSONObject delMember(String token,Map<String, String> uriVariables){
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
			uriVariables.put("captainId", SessionUtil.getCurUid()+"");
			HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity<>(postParameters, requestHeaders);
			ResponseEntity<String> resp = restTemplate.exchange(createUrl(delMember_url,uriVariables) ,HttpMethod.GET,r, String.class);

			return JSONObject.parseObject(resp.getBody());
		} catch (RestClientException e) {
			logger.error("call the getPersonalWealthInfo list from rest api occurred error,cause by:",e);
			return null;
		}
	}
	public JSONObject zydz(String token,Map<String, String> uriVariables){
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
			//uriVariables.put("newCaptainId", SessionUtil.getCurUid()+"");
			HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity<>(postParameters, requestHeaders);
			ResponseEntity<String> resp = restTemplate.exchange(createUrl(zyzd_url,uriVariables) ,HttpMethod.POST,r, String.class);

			return JSONObject.parseObject(resp.getBody());
		} catch (RestClientException e) {
			logger.error("call the getPersonalWealthInfo list from rest api occurred error,cause by:",e);
			return null;
		}
	}

	public JSONObject addTeamImg(List<MultipartFile> files){
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
			DefaultPutRet putRet=null;
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
						putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
					}catch (Exception e) {
						// TODO: handle exception
						failCount++;
					}
				}
			}
			jsonObject.put("code", 200);
			jsonObject.put("msg", putRet.key);
		} catch (RestClientException e) {
			logger.error("saveUserInfo to rest api occurred error,cause by:",e);
			jsonObject.put("code", 100);
			jsonObject.put("msg", "调用远程接口发生错误，请检联系管理员");
		}
		return  jsonObject;
	}
}
