package com.mvstream.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Slf4j
/* This class is for call movie-catlog-g-service for this USed RestTemplate*/
public class CallMovieCatalogInfo {

    /* path os movie-catalog-service*/
    public static final String movieCatalogInfoUrl = "http://api-gateway";

    /*RestTemplate*/
    public final RestTemplate restTemplate;

    public CallMovieCatalogInfo(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    /* This method calls movie-catalog-service micro and get path of the movie for display*/
    public String getMoviePath(long movieId) {

        var res = restTemplate.getForEntity(movieCatalogInfoUrl + "/movie-info/getPath/{id}", String.class, movieId);
        log.info("--->getMoviePath is executed and bring path<---" + res.getBody());
        return res.getBody();
    }


    /* the below is for WebClient */
/*
    public final WebClient webClient;

    public CallMovieCatalogInfo(WebClient webClient) {
        this.webClient = webClient;
    }

    public String getMoviePath(long movieId) {

       return webClient.get()
                .uri(movieCatalogInfoUrl + "/movie-info/getPath/{id}", movieId)
                .retrieve()
                .bodyToMono(String.class)
                .block();

    }
*/
}
