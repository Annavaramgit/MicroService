package com.mvstream.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    /* RestTemplate is a class which allow us to make micro-services communicate each other*/
    @LoadBalanced
    @Bean
    public RestTemplate rest(){
        return new RestTemplate();
    }
}
