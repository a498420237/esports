package cn.esports.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import cn.esports.config.BaseConfig;
import cn.esports.config.QiniuConfig;

/**
 * 
 * <p>
 * Title: BaseService
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author zhimin.hu
 * @date 2018年4月24日
 */
public class BaseService {

	private final String URL_PARAM_PREFIX = "?";

	private final String KEY_VALUE_PREFIX = "=";

	private final String PARAM_SPLIT_PREFIX = "&";

	public String TAPCLIENTTYPE = "2";

	public String TAPCLIENTVERSION = "1";

	@Autowired
	protected BaseConfig baseConfig;
	
	@Autowired
	protected QiniuConfig qiniuConfig;

	@Autowired
	protected RestTemplate restTemplate;

	protected String createUrl(String path, Map<String, String> uriVariables) {
		String url = baseConfig.getHttpUrl() + path;
		if (uriVariables == null) {
			return url;
		}
		url = url + URL_PARAM_PREFIX;

		for (Map.Entry<String, String> entry : uriVariables.entrySet()) {
			url += entry.getKey() + KEY_VALUE_PREFIX + entry.getValue()
					+ PARAM_SPLIT_PREFIX;
		}
		return url.substring(0, url.length() - 1);
	}
	
	protected MultiValueMap<String, Object> getPostParameters(Map<String, String> uriVariables)  
	{
		MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
		if (uriVariables == null) {
			return null;
		}
		for (Map.Entry<String, String> entry : uriVariables.entrySet()) {
			postParameters.add(entry.getKey(), entry.getValue());
		}
		return postParameters;
	}
}
