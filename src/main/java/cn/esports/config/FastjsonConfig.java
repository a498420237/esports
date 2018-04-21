package cn.esports.config;

import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;

@Configuration
public class FastjsonConfig {
	@Bean
	public HttpMessageConverters fastjsonConverter() {
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		// 自定义格式化输出
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
				SerializerFeature.WriteNullStringAsEmpty,
				SerializerFeature.WriteNullNumberAsZero);

		FastJsonHttpMessageConverter4 fastjson = new FastJsonHttpMessageConverter4();
		fastjson.setFastJsonConfig(fastJsonConfig);
		return new HttpMessageConverters(fastjson);
	}
}
