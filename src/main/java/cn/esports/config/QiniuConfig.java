package cn.esports.config;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 七牛云配置
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Component
@ConfigurationProperties(prefix="qiniuConfig")
public class QiniuConfig implements Serializable {

	private String accessKey;
	private String secretKey;
	private String bucket;
	public String getAccessKey() {
		return accessKey;
	}
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getBucket() {
		return bucket;
	}
	public void setBucket(String bucket) {
		this.bucket = bucket;
	}
	@Override
	public String toString() {
		return "QiniuConfig [accessKey=" + accessKey + ", secretKey=" + secretKey + ", bucket=" + bucket + "]";
	}
	
	
}
