package com.zhxy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcMyConfig extends WebMvcConfigurationSupport {
	
	/**
	 * 跨域
	 */
	@Override
	protected void addCorsMappings(CorsRegistry registry) {		
		registry.addMapping("/**");
	}
	/**
	 * 静态资源处理：
	 * 
	 */
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 	本工程
		registry.addResourceHandler("*").addResourceLocations("classpath:/static/");
	}
	
/*	*//**
	 * 普通字符串乱码
	 * @return
	 *//*
	@Bean
	public StringHttpMessageConverter stringMessageConverter() {
		StringHttpMessageConverter msg = new StringHttpMessageConverter(StandardCharsets.UTF_8);
		return msg;
	}
	
	@Bean
	public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
		FastJsonHttpMessageConverter fjc = new FastJsonHttpMessageConverter();
		fjc.setFeatures(SerializerFeature.DisableCircularReferenceDetect);
        return fjc;
	}
	
	
	@Override
	protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(stringMessageConverter());
		converters.add(fastJsonHttpMessageConverter());
		super.configureMessageConverters(converters);
	}
	*/
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		
	}	
}
