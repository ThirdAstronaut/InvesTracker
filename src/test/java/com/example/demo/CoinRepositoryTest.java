package com.example.demo;
import com.example.demo.domain.CoinMarketModel;
import com.example.demo.repository.CoinRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
@SpringBootTest
@RunWith(SpringRunner.class)
public class CoinRepositoryTest {

    @Autowired
    private CoinRepository coinRepository;


        @Test
        public void saveProduct(){
            CoinMarketModel coin = new CoinMarketModel().toBuilder().name("name").symbol("symbol").build();
            coin.setPrice_usd("1000");
            coin.setId("id");


            coinRepository.save(coin);

            Optional<CoinMarketModel> fetchedProduct = coinRepository.findById(coin.getId());

            assertNotNull(fetchedProduct);

            assertEquals(coin.getId(), fetchedProduct.get().getId());
            assertEquals(coin.getPrice_usd(), fetchedProduct.get().getPrice_usd());

            fetchedProduct.get().setPrice_usd("2000");
            coinRepository.save(fetchedProduct.get());

            Optional<CoinMarketModel> fetchedUpdatedProduct = coinRepository.findById(fetchedProduct.get().getId());
            assertEquals(fetchedProduct.get().getPrice_usd(), fetchedUpdatedProduct.get().getPrice_usd());

            coinRepository.delete(coin);

        }
}