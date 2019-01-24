package com.example.demo.repository;

import com.example.demo.domain.CoinMarketModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CoinRepository extends CrudRepository<CoinMarketModel, String> {
    CoinMarketModel findByName(String name);

    CoinMarketModel findBySymbol(String symbol);


    Iterable<CoinMarketModel> findAll();

    Optional<CoinMarketModel> findById(String id);

    CoinMarketModel save(CoinMarketModel coinMarketModel);

}