package cn.esports.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import com.alibaba.fastjson.JSONObject;


@Component
public class PayService extends BaseService {

	private static final Logger logger = LoggerFactory.getLogger(PersonalWealthService.class);

	public JSONObject getActivityList(Map<String, String> uriVariables) {
		try {
			//userId
			//uriVariables.put
			JSONObject resp = restTemplate.getForObject(createUrl("api/pay/getPayActivity.json",uriVariables), JSONObject.class);
			 return resp;
		} catch (RestClientException e) {
			logger.error("call the getUserHonoList list from rest api occurred error,cause by:",e);
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("code", 100);
			jsonObject.put("msg", "调用远程接口发生错误，请检联系管理员");
			return  jsonObject;
		}
	}
}
