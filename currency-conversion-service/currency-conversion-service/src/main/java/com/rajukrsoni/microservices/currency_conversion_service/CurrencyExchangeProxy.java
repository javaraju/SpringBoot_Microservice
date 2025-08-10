package com.rajukrsoni.microservices.currency_conversion_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@FeignClient(name="currency-exchange", url = "localhost:8000") before eureka server it was required but after eureka
//configuration load balancer automatically talk to naming server or discovery or registry server and get url form there
//and call to that url
@FeignClient(name="currency-exchange") //now url not required
public interface CurrencyExchangeProxy {

    @RequestMapping(value = "/currency-exchange/from/{from}/to/{to}", method = RequestMethod.GET)
    public CurrencyConversion retrieveExchangeValue(
            @PathVariable String from,
            @PathVariable String to);

}
