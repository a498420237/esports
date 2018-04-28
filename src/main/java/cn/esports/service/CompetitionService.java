package cn.esports.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * 赛事模块 业务层
 * @author liukunrui
 */
@Component
public class CompetitionService extends BaseService {
	
	private static final Logger logger = LoggerFactory.getLogger(CompetitionService.class);
	
	private static final String LIST_URL = "/match/findMatchListByApp.json";
	private static final String INFO_URL = "/match/findMatchInfoByApp.json";
	private static final String GAINST_PLAN_URL = "/match/findAgainstPlanByApp.json";
	
	/**
	 * 获取赛事列表
	 * @param uriVariables
	 * @return
	 */
	public JSONObject getCompetitions(Map<String, String> uriVariables) {
		try {
//			return restTemplate.getForObject(createUrl(LIST_URL, uriVariables),JSONObject.class);
			
			/*因为接口没有 状态 参数   所以如果要体现效果只能内存中过滤
			 * 以下是简单的内存过滤实现    后续接口完善了  直接使用上面的一行代码即可
			 */
			Integer offSet = Integer.parseInt(uriVariables.get("offset"));
			Integer limit = Integer.parseInt(uriVariables.get("limit"));
			String gameType = uriVariables.get("gameType");
			String statuType = uriVariables.get("statuType");
			uriVariables.remove("limit");//调接口不分页
			
			JSONObject result = restTemplate.getForObject(createUrl(LIST_URL, uriVariables),JSONObject.class);
			JSONObject t = result.getJSONObject(("t"));
			JSONArray results = t.getJSONArray("result");
			List<JSONObject> matchs = new ArrayList<JSONObject>();
			for(int i=0; i<results.size();i++){
				JSONObject o = results.getJSONObject(i);
				if(matchGameType(gameType, o) && matchStatuType(statuType, o)){
					matchs.add(o);
				}
			}

			//分页
			int start = (offSet - 1) * limit;
			if(start < 0 || start > matchs.size()){
				start = 0;
			}
			int toIndex = start + limit;
			if(toIndex > matchs.size()){
				toIndex = matchs.size();
			}
			List<JSONObject> subList = matchs.subList(start, toIndex);    
			
			JSONObject ret0 = new JSONObject();
			ret0.put("result", subList);
			ret0.put("total", matchs.size());
			JSONObject ret = new JSONObject();
			ret.put("t", ret0);
			return ret;
		} catch (RestClientException e) {
			logger.error("call the forecast list from rest api occurred error,cause by:",e);
			return null;
		}
	}
	
	private boolean matchStatuType(String statuType, JSONObject o) {
		String matchStatus = o.getString("matchStatus") ;
		return statuType.equals(matchStatus);
	}

	private boolean matchGameType(String gameType, JSONObject o) {
		return "-1".equals(gameType) || gameType.equals(o.getString("gameType"));
	}
	
	/**
	 * 获取赛事详情
	 * @param matchId
	 * @return
	 */
	public Object getCompetitionInfo(Long matchId) {
		try {
			Map<String, String> uriVariables = new HashMap<String, String>();
			uriVariables.put("matchId", String.valueOf(matchId));
			JSONObject result = restTemplate.getForObject(createUrl(INFO_URL, uriVariables),JSONObject.class);
			
			Object competition = result.get("t");
			return competition;
		} catch (RestClientException e) {
			logger.error("call the forecast list from rest api occurred error,cause by:",e);
			return null;
		}
	}
	
	/**
	 * 获取轮次信息
	 * @param matchId
	 * @param num  轮次号
	 * @return
	 */
	public JSONObject getAgainstPlan(Long matchId, int num) {
		try {
			Map<String, String> uriVariables = new HashMap<String, String>();
			uriVariables.put("matchId", String.valueOf(matchId));
			if(num > 0){
				uriVariables.put("num", String.valueOf(num));
			}
			return restTemplate.getForObject(createUrl(GAINST_PLAN_URL, uriVariables),JSONObject.class);
		} catch (RestClientException e) {
			logger.error("call the forecast list from rest api occurred error,cause by:",e);
			return null;
		}
	}

}
