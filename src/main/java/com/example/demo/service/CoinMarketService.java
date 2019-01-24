package com.example.demo.service;

import com.example.demo.domain.CoinMarketModel;

import java.util.List;


public interface CoinMarketService {

    CoinMarketModel findByName(String name);

    List<String> getAllNames();

    CoinMarketModel findBySymbol(String symbol);

    boolean coinExistByName(String name);

    List<String> coinNames();

}
