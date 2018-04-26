package cn.esports.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.LinkedMultiValueMap;


import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RestTemplateUtils {

    @Autowired
    public RestTemplate restTemplate;

    private static class DefaultResponseErrorHandler implements ResponseErrorHandler {

        @Override
        public boolean hasError(ClientHttpResponse response) throws IOException {
            return response.getStatusCode().value() != HttpServletResponse.SC_OK;
        }

        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(response.getBody()));
            StringBuilder sb = new StringBuilder();
            String str = null;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            try {
                throw new Exception(sb.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * @param url
     * @param jsonParams
     * @return
     * @Title: get
     * @author: gu
     * @Description: TODO
     * @return: String
     */
    public static String get(String url, String jsonParams) {

        JSONObject obj = JSONObject.parseObject(jsonParams);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
        String result = restTemplate.getForObject(expandURL(url, obj.keySet()), String.class, obj);
        return result;
    }


    /**
     * @param url
     * @param jsonParams
     * @return
     * @Title: get
     * @author: gu
     * @Description: TODO
     * @return: T
     */
    public static <T> T get(String url, String jsonParams, Class<T> clz) {

        JSONObject obj = JSONObject.parseObject(jsonParams);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
        T result = restTemplate.getForObject(expandURL(url, obj.keySet()), clz, obj);
        return result;
    }

    /**
     * @param url
     * @param jsonParams
     * @param jsonHeadParams
     * @param mediaType
     * @param clz
     * @return
     * @Title: post
     * @author: gu
     * @Description: 发送json或者form格式数据
     * @return: T
     */
    public static <T> T post(String url, String jsonParams, String jsonHeadParams, Class<T> clz, MediaType mediaType) {

        if (mediaType == null) {
            mediaType = MediaType.APPLICATION_FORM_URLENCODED;
        }

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(mediaType);

        if (jsonHeadParams != null) {

            for (String key : JSONObject.parseObject(jsonHeadParams).keySet()) {
                requestHeaders.add(key, JSONObject.parseObject(jsonHeadParams).getString(key));
            }
        }

        JSONObject jsonObject = JSONObject.parseObject(jsonParams);

        HttpEntity<?> requestEntity = (
                mediaType == MediaType.APPLICATION_JSON
                        || mediaType == MediaType.APPLICATION_JSON_UTF8)
                ? new HttpEntity<>(jsonObject, requestHeaders)
                : (mediaType == MediaType.APPLICATION_FORM_URLENCODED
                ? new HttpEntity<org.springframework.util.MultiValueMap>(createMultiValueMap(jsonObject), requestHeaders)
                : new HttpEntity<>(null, requestHeaders));

        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());

        T result = (mediaType == MediaType.APPLICATION_JSON || mediaType == MediaType.APPLICATION_JSON_UTF8)
                ? restTemplate.postForObject(url, requestEntity, clz)
                : restTemplate.postForObject(mediaType == MediaType.APPLICATION_FORM_URLENCODED
                ? url
                : expandURL(url, jsonObject.keySet()), requestEntity, clz, jsonObject);

        return result;
    }


    /**
     * @param url
     * @param keys
     * @return
     * @Title: expandURL
     * @author: gu
     * @Description: TODO
     * @return: String
     */
    private static String expandURL(String url, Set<?> keys) {
        final Pattern QUERY_PARAM_PATTERN = Pattern.compile("([^&=]+)(=?)([^&]+)?");
        Matcher mc = QUERY_PARAM_PATTERN.matcher(url);
        StringBuilder sb = new StringBuilder(url);
        if (mc.find()) {
            sb.append("&");
        } else {
            sb.append("?");
        }

        for (Object key : keys) {
            sb.append(key).append("=").append("{").append(key).append("}").append("&");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    private static org.springframework.util.MultiValueMap<String, String> createMultiValueMap(JSONObject params) {
        org.springframework.util.MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        for (String key : params.keySet()) {
            if (params.get(key) instanceof List) {
                for (Iterator<String> it = ((List<String>) params.get(key)).iterator(); it.hasNext(); ) {
                    String value = it.next();
                    map.add(key, value);
                }
            } else {
                map.add(key, params.getString(key));
            }
        }
        return map;
    }
}
