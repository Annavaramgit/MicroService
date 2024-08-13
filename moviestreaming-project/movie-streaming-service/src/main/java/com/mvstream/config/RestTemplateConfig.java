package com.mvstream.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
/* This class is for configuration of RestTemplate class */

public class RestTemplateConfig {

    /* RestTemplate is a class which allow us to make micro-services communicate each other*/

    @LoadBalanced
    @Bean
    public RestTemplate rest(){
        return new RestTemplate();
    }

    //the below is for WebClient
    /*
    @Bean
    @LoadBalanced
    WebClient.Builder createLoadBalancedBuilder() { return WebClient.builder(); }

    @Bean
    WebClient client(WebClient.Builder builder) { return builder.build(); }
     */
}
