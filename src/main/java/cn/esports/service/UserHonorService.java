package cn.esports.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import com.alibaba.fastjson.JSONObject;

import cn.esports.utils.SessionUtil;
/**
 * 个人荣誉
 * @author huzhimin
 *
 */
@Component
public class UserHonorService extends BaseService {

	private static final Logger logger = LoggerFactory.getLogger(PersonalWealthService.class);

	public JSONObject getUserHonoList(String token,Map<String, String> uriVariables) {
		try {
			uriVariables.put("userId", String.valueOf( SessionUtil.getCurUid()));
			 ResponseEntity<String> resp = restTemplate.getForEntity(createUrl("match/findUserHonorList.json",uriVariables) , String.class);
			 return JSONObject.parseObject(resp.getBody());
		} catch (RestClientException e) {
			logger.error("call the getUserHonoList list from rest api occurred error,cause by:",e);
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("code", 100);
			jsonObject.put("msg", "调用远程接口发生错误，请检联系管理员");
			return  jsonObject;
		}
	}
}
