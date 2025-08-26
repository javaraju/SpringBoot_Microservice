package com.rajukrsoni.microservices.currency_exchange_service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

    @Autowired
    private Environment environment;
    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    @RequestMapping(value = "/currency-exchange/from/{from}/to/{to}", method = RequestMethod.GET)
    public CurrencyExchange retrieveExchangeValue(
            @PathVariable String from,
            @PathVariable String to){

        logger.info("retrieveExchangeValue called with {} to {}", from, to);
//        [currency-exchange] [nio-8000-exec-1] [d007ab1b1f4dba8e7207a8eac7cad84e-a95b4efb5fae8362]
//        c.r.m.c.CurrencyExchangeController       : retrieveExchangeValue called with USD to INR

//        here logger gave above message in console with some id ([d007ab1b1f4dba8e7207a8eac7cad84e-a95b4efb5fae8362] )
//        that is assing for every request by micrometer (dependency check in pom)


//        CurrencyExchange currencyExchange = new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(50));
        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);

        if (currencyExchange == null){
            throw new RuntimeException("Unable to find data from " +from + " to " +to);
        }
        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);
        return currencyExchange;

    }


}
