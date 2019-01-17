package com.example.demo.controller;

import com.example.demo.domain.CoinMarketModel;
import com.example.demo.domain.Transactions;
import com.example.demo.domain.Users;
import com.example.demo.domain.enumeration.Currency;
import com.example.demo.domain.enumeration.TransactionType;
import com.example.demo.repository.CoinRepository;
import com.example.demo.repository.UsersRepository;
import com.example.demo.service.CoinMarketService;
import com.example.demo.validators.TransactionValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Controller
public class TransactionController {

    private final CoinMarketService coinMarketService;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    CoinRepository coinRepository;

    private final TransactionValidator transactionValidator;

    @Autowired
    public TransactionController(CoinMarketService coinMarketService, TransactionValidator transactionValidator) {
        this.coinMarketService = coinMarketService;
        this.transactionValidator = transactionValidator;
    }


    @InitBinder("transaction")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(transactionValidator);
    }

    @GetMapping("/transaction")
    public String transactionForm(Model model) {
        Transactions transactions = new Transactions();
        transactions.setDate(LocalDate.now());
        transactions.setValue(0.0);
        transactions.setCryptocurrencies(new CoinMarketModel());
        model.addAttribute("transaction", transactions);
        model.addAttribute("coins", coinMarketService.coinNames());

        //TODO
        Optional<Users> users = usersRepository.findById(6L);
        log.error(users.get().getWallet().getCryptocurrencies().keySet().size() + "SIZE przed");
/*

        users.get().getWallet().getCryptocurrencies().put(coinRepository.findByName("bitcoin"),1.0);
usersRepository.save(users.get());
        log.error(users.get().getWallet().getCryptocurrencies().keySet().size() + "SIZE");
*/




        users.get().getWallet().getTransactions().add(new Transactions().cryptocurrencies(coinRepository.findByName("bitcoin")).amount(1.0).type(TransactionType.Buy));
        usersRepository.save(users.get());
        log.error(users.get().getWallet().getTransactions().size() + "SIZE");



        return "form3";
    }


    @PostMapping("/transaction")
    public String checkPersonInfo(@Valid @ModelAttribute("transaction") Transactions transaction, BindingResult bindingResult, Model model) {
//save transaction
        if (bindingResult.hasErrors()) {
//            log.error("żyje!!!!!");
//            log.error(bindingResult.toString());
            model.addAttribute("wrongName", true);
            return "form3";
        } else {
            // model.addAttribute("transaction", transactions);
            transaction.setValue(transaction.getPrice() * transaction.getAmount());

            Optional<Users > users = usersRepository.findById(6L);
            if (transaction.getType() == TransactionType.Buy) {  //kupuje
                log.error("kupuje");

                if (transaction.getCurrency() == Currency.PLN) {
                    if (users.get().getWallet().getOwnedPLN() >= transaction.getValue()) {
                        log.error("mma hajs");

                        users.get().getWallet().setOwnedPLN(users.get().getWallet().getOwnedPLN() - transaction.getValue()); // pomniejsz saldo
                        log.error("pomniejszylem");

 // TODO                       usersRepository.save(users);

                        for (CoinMarketModel coin : users.get().getWallet().getCryptocurrencies().keySet()) {           //sprawdz czy isteniej i albo dopisz albo dodaj
                            log.error("for");

                            if (coin.getName().equals(transaction.getCryptocurrencies().getName())) {
                                log.error("if");

                                Map<CoinMarketModel, Double> map = users.get().getWallet().getCryptocurrencies();
                                map.put(coin, map.get(coin) + transaction.getAmount());
                                model.addAttribute("works", true);
                                log.error("dopisalem");

                                return "form3";

                            } else {
                                Map<CoinMarketModel, Double> map = users.get().getWallet().getCryptocurrencies();
                                map.put(coin, transaction.getAmount());
                                model.addAttribute("works", true);
                                return "form3";

                            }
                        }
                    } else {
                        model.addAttribute("works", false); //brak hajsu
                        return "form3";

                    }
                } else {
                    if (users.get().getWallet().getOwnedUSD() >= transaction.getValue()) {
                        users.get().getWallet().setOwnedUSD(users.get().getWallet().getOwnedUSD() - transaction.getValue()); // pomniejsz saldo
                        for (CoinMarketModel coin : users.get().getWallet().getCryptocurrencies().keySet()) {           //sprawdz czy isteniej i albo dopisz albo dodaj
                            if (coin.getId().equals(transaction.getCryptocurrencies().getId())) {
                                Map<CoinMarketModel, Double> map = users.get().getWallet().getCryptocurrencies();
                                map.put(coin, map.get(coin) + transaction.getAmount());
                                model.addAttribute("works", true);
                                return "form3";

                            } else {
                                Map<CoinMarketModel, Double> map = users.get().getWallet().getCryptocurrencies();
                                map.put(coin, transaction.getAmount());
                                model.addAttribute("works", true);
                                return "form3";

                            }
                        }
                    } else {
                        model.addAttribute("works", false); //brak hajsu
                        return "form3";

                    }
                }
            } else {    //sprzedaje
                for (CoinMarketModel coin : users.get().getWallet().getCryptocurrencies().keySet()) {
                    if (coin.getId().equals(transaction.getCryptocurrencies().getId())) {
                        Map<CoinMarketModel, Double> map = users.get().getWallet().getCryptocurrencies();
                        if (map.get(coin) >= transaction.getAmount()) {    //jesli mozesz sprzedac
                            map.put(coin, map.get(coin) - transaction.getAmount()); //odejmij sprzedane
                            if (transaction.getCurrency() == Currency.PLN) {  //uaktualnij konto o wartosc transakcji
                                users.get().getWallet().setOwnedPLN(users.get().getWallet().getOwnedPLN() + transaction.getValue());
                                model.addAttribute("works", true);
                                return "form3";

                            } else {
                                users.get().getWallet().setOwnedUSD(users.get().getWallet().getOwnedUSD() + transaction.getValue());
                                model.addAttribute("works", true);
                                return "form3";

                            }

                        } else {
                            model.addAttribute("works", false);
                            return "form3";

                            // TODO warning ze brak coinow;
                        }
                    }

                }
                model.addAttribute("works", false);
                return "form3";

                //TODO return ze nie ma takiego coina u cb

            }
        }
        return "form3";
    }


}
