package cn.esports.service;

import cn.esports.utils.SessionUtil;
import com.alibaba.fastjson.JSONObject;
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

import java.util.HashMap;
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
			//uriVariables.put("userId", SessionUtil.getCurUid()+"");
			uriVariables.put("userId", "139");
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
			//uriVariables.put("userId", SessionUtil.getCurUid()+"");
			uriVariables.put("userId", "139");
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
			//uriVariables.put("userId", SessionUtil.getCurUid()+"");
			uriVariables.put("userId", "139");
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
			//uriVariables.put("userId", SessionUtil.getCurUid()+"");
			uriVariables.put("userId", "139");
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
			//uriVariables.put("captainId", SessionUtil.getCurUid()+"");
			uriVariables.put("captainId", "139");
			HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity<>(postParameters, requestHeaders);
			ResponseEntity<String> resp = restTemplate.exchange(createUrl(edit_team_url,uriVariables) ,HttpMethod.GET,r, String.class);

			return JSONObject.parseObject(resp.getBody());
		} catch (RestClientException e) {
			logger.error("call the getPersonalWealthInfo list from rest api occurred error,cause by:",e);
			return null;
		}
	}
}
