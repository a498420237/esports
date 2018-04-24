package cn.esports.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import cn.esports.config.BaseConfig;
/**
 * 
* <p>Title: BaseService</p>  
* <p>Description: </p>  
* @author zhimin.hu  
* @date 2018年4月24日
 */
public class BaseService {
	
	@Autowired
	public BaseConfig baseConfig;
	
	@Autowired
	public RestTemplate restTemplate ;
}
