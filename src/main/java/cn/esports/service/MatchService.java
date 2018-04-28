package cn.esports.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import cn.esports.entity.MatchInfo;
import cn.esports.entity.UserInfo;

/**
 * 
* <p>Title: MatchService</p>  
* <p>Description: </p>  
* @author zhimin.hu  
* @date 2018年4月25日
 */
@Component
public class MatchService extends BaseService {

	/**
	 * 获取比赛信息 首页
	 * 
	 * @return
	 */
	public MatchInfo.TBean GetIndexList(String token,int gameType) {
		MatchInfo.TBean tBean = new MatchInfo.TBean();
		try {
			String url = baseConfig.getHttpUrl() + "/match/findMatchListByApp.json";
			// 增加header 请求头
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("TAP-CLIENT-TYPE", "0"); // 0web前端 （2：安卓 3：iOS）
			requestHeaders.add("TAP-CLIENT-VERSION", "0.001"); // 客户端版本
			requestHeaders.add("TAP-CLIENT-TOKEN", token); // 客户端版本
			requestHeaders.add("Content-Type", "application/x-www-form-urlencoded");
			MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
			//postParameters.add("token", mobile);
//			postParameters.add("type", typeEnum.name());
			postParameters.add("gameType", gameType); //游戏类型
			HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity<>(postParameters, requestHeaders);
			MatchInfo models = restTemplate.postForObject(url, r, MatchInfo.class);
			tBean=models.getT();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tBean;
	}
}
