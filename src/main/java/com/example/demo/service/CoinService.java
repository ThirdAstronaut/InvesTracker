package com.example.demo.service;
/*
import com.example.demo.domain.Cryptocurrencies;
import com.example.demo.repository.CryptocurrenciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class CoinService {
    private final CryptocurrenciesRepository cryptocurrenciesRepository;

    @Autowired
    public CoinService(CryptocurrenciesRepository cryptocurrenciesRepository) {
        this.cryptocurrenciesRepository = cryptocurrenciesRepository;
    }


    public List<Cryptocurrencies> fetchData(){
        RestTemplate restTemplate = new RestTemplate();

//Create a list for the message converters

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();

//Add the Jackson Message converter
        messageConverters.add(new MappingJackson2HttpMessageConverter());

//Add the message converters to the restTemplate
        restTemplate.setMessageConverters(messageConverters);

        List<Cryptocurrencies> resultList = Arrays.asList(restTemplate.getForObject("https://api.coinmarketcap.com/v1/ticker/?limit=10", Cryptocurrencies[].class));
        //  logger.info("reusltList size " + resultList.size());

        //    coinService.saveAll(resultList);
        return resultList;
    }

    @Scheduled(initialDelay = 1_000, fixedRate = 1_000)
    private void saveCoins(){
        cryptocurrenciesRepository.saveAll(fetchData());
    }

}*/
