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
import static org.junit.Assert.assertNull;
@SpringBootTest
@RunWith(SpringRunner.class)
public class CoinRepositoryTest_1{

    @Autowired
    private CoinRepository coinRepository;


        @Test
        public void testSaveProduct(){
            //setup product
            CoinMarketModel coin = new CoinMarketModel().toBuilder().name("name").symbol("symbol").build(); ;
            coin.setPrice_usd("1000");
coin.setId("id");
            /*assertNull(coin.getId()); //null before save
            coinRepository.save(coin);
            assertNotNull(coin.getId()); //not null after save
*/
            //fetch from DB

            Iterable<CoinMarketModel> products = coinRepository.findAll();

            int count = 0;

            for(CoinMarketModel coinMarketModel : products){
                count++;
            }

            coinRepository.save(coin);

            Optional<CoinMarketModel> fetchedProduct = coinRepository.findById(coin.getId());

            //should not be null
            assertNotNull(fetchedProduct);

            //should equal
            assertEquals(coin.getId(), fetchedProduct.get().getId());
            assertEquals(coin.getPrice_usd(), fetchedProduct.get().getPrice_usd());

            //update description and save
            fetchedProduct.get().setPrice_usd("2000");
            coinRepository.save(fetchedProduct.get());

            //get from DB, should be updated
            Optional<CoinMarketModel> fetchedUpdatedProduct = coinRepository.findById(fetchedProduct.get().getId());
            assertEquals(fetchedProduct.get().getPrice_usd(), fetchedUpdatedProduct.get().getPrice_usd());

            //verify count of products in DB

            //get all products, list should only have one


            //assertEquals(count + 1, coinRepository.count());

            coinRepository.delete(coin);

        }
}