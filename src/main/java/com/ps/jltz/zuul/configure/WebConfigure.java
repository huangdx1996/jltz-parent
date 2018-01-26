package com.ps.jltz.zuul.configure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ps.jltz.zuul.web.interceptor.CrossInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/23/17
 * @since 0.1
 */
@Configuration
public class WebConfigure extends WebMvcConfigurerAdapter {
    @Bean
    public CrossInterceptor crossInterceptor() {
        return new CrossInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(crossInterceptor());
    }

}
