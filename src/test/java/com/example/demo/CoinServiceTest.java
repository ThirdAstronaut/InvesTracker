package com.example.demo;

import com.example.demo.domain.CoinMarketModel;
import com.example.demo.repository.CoinRepository;
import com.example.demo.service.CoinMarketService;
import com.example.demo.service.CoinMarketServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
public class CoinServiceTest {

    CoinMarketService coinMarketService;

    @Mock
    CoinRepository coinRepository;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        coinMarketService = new CoinMarketServiceImpl(coinRepository);
    }

    @Test
    public void getAllNames() throws Exception {

        CoinMarketModel coin = new CoinMarketModel().toBuilder().name("testName").build();
        List<String> coinData = new ArrayList<>();
        coinData.add(coin.getName());

        when(coinMarketService.getAllNames()).thenReturn(coinData);

        List<String> coins = coinMarketService.getAllNames();

        assertEquals(coins.size(), 1);
        verify(coinRepository, times(1)).findAll();
    }


}
