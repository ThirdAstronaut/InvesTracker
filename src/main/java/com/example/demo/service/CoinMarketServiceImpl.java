package com.example.demo.service;

import com.example.demo.domain.CoinMarketModel;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service("coinMarketService")
public class CoinMarketServiceImpl implements CoinMarketService{
    private final CoinRepository coinRepository;

    @Autowired
    public CoinMarketServiceImpl(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }

    /**
     * @return List of CoinMarketModels got from web API
     */
    private List<CoinMarketModel> fetchData(){
        RestTemplate restTemplate = new RestTemplate();

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();

        messageConverters.add(new MappingJackson2HttpMessageConverter());

        restTemplate.setMessageConverters(messageConverters);

        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject("https://api.coinmarketcap.com/v1/ticker/?limit=10", CoinMarketModel[].class)));
    }

    /**
     *
     * @return list of CoinMarketModels names stored in database
     */
    public List<String> coinNames(){
        List<String> list = new ArrayList<>();
        for(CoinMarketModel coin : coinRepository.findAll()) {
            list.add(coin.getName());
/*            list.add(coin.getSymbol());*/
        }
        return list;
    }

    /**
     *
     * @param name CoinMarketModel's name to search
     * @return whether such coin exist in database
     */
    public boolean coinExist(String name){
        return coinRepository.findByName(name) != null /*|| coinRepository.findBySymbol(name) != null*/;
    }



    /**
     * Scheduled method to fetch CoinMarketModel data from web API
     */
    //@Async
    @Scheduled(initialDelay = 6_000_000, fixedRate = 6_000_000)
    private void saveCoins(){
        coinRepository.saveAll(fetchData());
    }

    /**
     * @param name CoinMarketModel's name to find
     * @return CoinMarketModel stored in database or else throws NotFoundException
     */
    @Override
    public CoinMarketModel findByName(String name) {

        CoinMarketModel coin = coinRepository.findByName(name);

        if (coin == null) {
            throw new NotFoundException("Coin Not Found. For name value: " + name);
        }

        return coin;
    }

    @Override
    public List<String> getAllNames() {
        List<String> set = new ArrayList<>();

        Iterator iterator = coinRepository.findAll().iterator();
        while( iterator.hasNext() ) {
            String e = (String) iterator.next();
            set.add(e);

        }


        return set;
    }




    @Override
    public CoinMarketModel findBySymbol(String symbol) {

        CoinMarketModel coin = coinRepository.findBySymbol(symbol);

        if (coin == null) {
            throw new NotFoundException("Coin Not Found. For symbol value: " + symbol);
        }

        return coin;
    }
}