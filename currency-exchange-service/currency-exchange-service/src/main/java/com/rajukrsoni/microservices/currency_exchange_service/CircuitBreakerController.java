package com.rajukrsoni.microservices.currency_exchange_service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
//    @Retry(name = "sample-api", fallbackMethod = "hardCodedResponse")
    @CircuitBreaker(name = "default", fallbackMethod = "hardCodedResponse") // there are lots of feature in circuitBreaker goto documentation
    public String sampleApi(){

        logger.info("Sample api call received");
        ResponseEntity<String> responseEntity = new RestTemplate().getForEntity("http://localhost:8080/dummy-api-call", String.class);

        return responseEntity.getBody();
    }

    private String hardCodedResponse(Exception ex){
        return "fall-back-response";
    }
}
