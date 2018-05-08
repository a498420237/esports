package cn.esports.service;

import java.util.Map;

import cn.esports.entity.PayInfo;
import cn.esports.utils.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import com.alibaba.fastjson.JSONObject;


@Component
public class PayService extends BaseService {

    private static final Logger logger = LoggerFactory.getLogger(PersonalWealthService.class);

    public JSONObject getActivityList(Map<String, String> uriVariables) {
        try {
            //userId
            //uriVariables.put
            JSONObject resp = restTemplate.getForObject(createUrl("api/pay/getPayActivity.json", uriVariables), JSONObject.class);
            return resp;
        } catch (RestClientException e) {
            logger.error("call the getActivityList list from rest api occurred error,cause by:", e);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", 100);
            jsonObject.put("msg", "调用远程接口发生错误，请检联系管理员");
            return jsonObject;
        }
    }

    //创建订单
    public PayInfo createOrder(Map<String, String> uriVariables, String payType) {

        try {

            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.add("TAP-CLIENT-TYPE", "0"); // 0web前端 （2：安卓 3：iOS）
            requestHeaders.add("TAP-CLIENT-VERSION", "0.001"); // 客户端版本
            requestHeaders.add("TAP-CLIENT-TOKEN", SessionUtil.getCurToken()); // 客户端版本
            requestHeaders.add("Content-Type", "application/x-www-form-urlencoded");

            if (payType.equals("alipay")) {

                MultiValueMap<String, Object> postParameters = getPostParameters(uriVariables);
                HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(postParameters, requestHeaders);
                PayInfo resp = restTemplate.postForObject(createUrl("api/pay/createOrder.json", uriVariables), httpEntity, PayInfo.class);
                return resp;
            } else if (payType.equals("wxpay")) {

                MultiValueMap<String, Object> postParameters = getPostParameters(uriVariables);
                HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(postParameters, requestHeaders);
                PayInfo resp = restTemplate.postForObject(createUrl("api/pay/createOrder.json", uriVariables), httpEntity, PayInfo.class);

                return resp;
            }

        } catch (RestClientException e) {
            logger.error("createOrder to rest api occurred error,cause by:", e);
        }
        return null;
    }
}
