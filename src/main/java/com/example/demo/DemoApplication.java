package com.example.demo;

import com.example.demo.domain.CoinMarketModel;
import com.example.demo.domain.Users;
import com.example.demo.domain.Wallets;
import com.example.demo.domain.enumeration.AccountStatus;
import com.example.demo.domain.enumeration.Sex;
import com.example.demo.repository.CoinRepository;
import com.example.demo.repository.UsersRepository;
import com.example.demo.service.CoinMarketService;
import com.example.demo.service.CoinMarketServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.text.SimpleDateFormat;
import java.time.Instant;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@EnableAsync
@SpringBootApplication(scanBasePackages={"com.example.demo"})
@EnableScheduling
@EnableAutoConfiguration
@ComponentScan("com.example.demo")
@EnableJpaRepositories("com.example.demo.repository")
public class DemoApplication implements CommandLineRunner {

@Autowired
UsersRepository usersRepository;

@Autowired
    CoinMarketService coinMarketService;

    @Autowired
    CoinRepository coinRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
/*Users user = new Users().name("test").accountStatus(AccountStatus.Active).age(21).birthDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("1998-01-01").getTime()).toInstant()).email("aaa@as.a").surname("a").sex(Sex.Male);
Wallets wallet =        new Wallets();
//        Set<CoinMarketModel> set = new HashSet<>();
//        set.add(coinRepository.findByName("bitcoin"));
//wallet.setCryptos(set);

wallet.getCryptocurrencies().put(coinRepository.findByName("bitcoin"),1.0);
wallet.setOwnedPLN(1000.0);
        wallet.setOwnedUSD(1000.0);

user.setWallet(wallet);

        usersRepository.save(user);
*/

       /* Users users = usersRepository.getOne(6L);
        users.getWallet().getCryptocurrencies().put(coinRepository.findByName("bitcoin"),1.0);*/
    }
}
