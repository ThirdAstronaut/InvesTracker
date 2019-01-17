package com.example.demo;

import com.example.demo.domain.CoinMarketModel;
import com.example.demo.repository.CoinRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
/*
@RunWith(SpringRunner.class)
@DataJpaTest
public class CoinRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired

    private CoinRepository coinRepository;

    @Test
    public void testFindByName() {

        entityManager.persist(new CoinMarketModel("1","Foo","symbol", "1","1","1","1","1","1","1","1","1","1","1", null));

        CoinMarketModel coin = coinRepository.findByName("Foo");

        assertEquals("Foo", coin.getName());
    }
}
*/