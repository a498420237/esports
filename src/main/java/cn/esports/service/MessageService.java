package cn.esports.service;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import java.util.Map;

@Component
public class MessageService extends BaseService {

    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    public JSONObject searchByKey(Map<String, String> uriVariables) {
        try {
            return restTemplate.getForObject(createUrl("/app/infoContentSearch.json", uriVariables), JSONObject.class);
        } catch (RestClientException e) {
            logger.error("get the message list from rest api occurred error,cause by:", e);
            return null;
        }
    }

    public JSONObject getTitles(Map<String, String> uriVariables) {
        try {
            return restTemplate.getForObject(createUrl("/app/findInfoChannelNameForApp.json", uriVariables), JSONObject.class);
        } catch (RestClientException e) {
            logger.error("get the message list from rest api occurred error,cause by:", e);
            return null;
        }
    }

    public JSONObject getInformations(Map<String, String> uriVariables) {
        try {
            return restTemplate.getForObject(createUrl("/app/findInfoContentListForApp.json", uriVariables), JSONObject.class);
        } catch (RestClientException e) {
            logger.error("get the message list from rest api occurred error,cause by:", e);
            return null;
        }
    }

    public JSONObject messageDetail(Map<String, String> uriVariables) {
        try {
            String url = "http://39.106.145.173:8089/web/findInfoContent.json?id=" + uriVariables.get("id");
            return restTemplate.getForObject(url, JSONObject.class);
        } catch (RestClientException e) {
            logger.error("get the message detail from rest api occurred error,cause by:", e);
            return null;
        }
    }
}
