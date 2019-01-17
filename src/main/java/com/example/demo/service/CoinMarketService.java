package com.example.demo.service;

import com.example.demo.domain.CoinMarketModel;

import java.util.List;
import java.util.Set;


public interface CoinMarketService {

    CoinMarketModel findByName(String name);
    List<String> getAllNames();
    CoinMarketModel findBySymbol(String symbol);
    boolean coinExist(String name);
    List<String> coinNames();

    }
