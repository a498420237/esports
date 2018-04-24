package cn.esports.config;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 
* <p>Title: BaseConfig</p>  
* <p>Description: </p>  
* @author zhimin.hu  
* @date 2018年4月24日
 */
@SuppressWarnings("serial")
@Component
@ConfigurationProperties(prefix="baseConfig")
public class BaseConfig implements Serializable {
	
	private String httpUrl;
	private String httpMethod;
	private String httpResultType;

	public String getHttpUrl() {
		return httpUrl;
	}

	public void setHttpUrl(String httpUrl) {
		this.httpUrl = httpUrl;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getHttpResultType() {
		return httpResultType;
	}

	public void setHttpResultType(String httpResultType) {
		this.httpResultType = httpResultType;
	}

	@Override
	public String toString() {
		return "BaseConfig [httpUrl=" + httpUrl + ", httpMethod=" + httpMethod + ", httpResultType=" + httpResultType
				+ "]";
	}
	
	
}
