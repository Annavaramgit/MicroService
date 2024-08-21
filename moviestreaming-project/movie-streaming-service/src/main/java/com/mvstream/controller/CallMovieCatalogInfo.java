package com.mvstream.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Slf4j
/* This class is for call movie-catlog-g-service for this USed RestTemplate*/
public class CallMovieCatalogInfo {

    public static final String MOVIE_SERVICE="movie-stream";

    /* path os movie-catalog-service*/
    public static final String movieCatalogInfoUrl = "http://api-gateway";

    /*RestTemplate*/
    public final RestTemplate restTemplate;

    public CallMovieCatalogInfo(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    /* This method calls movie-catalog-service micro and get path of the movie for display*/
    @CircuitBreaker(name = "movie-stream",fallbackMethod = "handleCiruiteBreakerMethod")
    public String getMoviePath(long movieId) {

        var res = restTemplate.getForEntity(movieCatalogInfoUrl + "/movie-info/getPath/{id}", String.class, movieId);
        log.info("--->getMoviePath is executed and bring path<---" + res.getBody());
        return res.getBody();
    }

   public String handleCiruiteBreakerMethod(long movieId,Throwable  e){
        log.info("fall back method executed....");
        return "something went wrong in the movie's list ! So try again after some time.... ";
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
