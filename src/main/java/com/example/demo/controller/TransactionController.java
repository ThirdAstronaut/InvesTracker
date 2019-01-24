package com.example.demo.controller;

import com.example.demo.domain.Transactions;
import com.example.demo.domain.Users;
import com.example.demo.domain.enumeration.Currency;
import com.example.demo.domain.enumeration.TransactionType;
import com.example.demo.repository.CoinRepository;
import com.example.demo.repository.TransactionsRepository;
import com.example.demo.repository.UsersRepository;
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
import java.util.Optional;

@Slf4j
@Controller
public class TransactionController {

    private final UsersRepository usersRepository;

    private final CoinRepository coinRepository;

    private final TransactionsRepository transactionsRepository;

    private final TransactionValidator transactionValidator;

    @Autowired
    public TransactionController(TransactionValidator transactionValidator, UsersRepository usersRepository, CoinRepository coinRepository, TransactionsRepository transactionsRepository) {
        this.transactionValidator = transactionValidator;
        this.usersRepository = usersRepository;
        this.coinRepository = coinRepository;
        this.transactionsRepository = transactionsRepository;
    }


    @InitBinder("transaction")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(transactionValidator);
    }

    /**
     * @param model view to return
     * @return form view
     */
    @GetMapping("/transaction")
    public String transactionForm(Model model) {
        Transactions transactions = new Transactions();
        model.addAttribute("transaction", transactions);
        model.addAttribute("works", 0);
        return "form3";
    }

    /**
     * @param transaction   validated Transaction object that was partially filled in transaction form
     * @param bindingResult whether passed data is valid
     * @param model         view to return
     * @return form view
     */
    @PostMapping("/transaction")
    public String checkTransactionInfo(@Valid @ModelAttribute("transaction") Transactions transaction, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("wrongName", true);
            return "form3";
        } else {
            Optional<Users> users = usersRepository.findById(4L);
            transaction.setWallets(users.get().getWallet());
            transaction.setValue(transaction.getPrice() * transaction.getAmount());
            transaction.setDate(LocalDate.now());
            transaction.setCoin(coinRepository.findByName(transaction.getCoinName()));
            if (transaction.getType() == TransactionType.Buy) {  //kupuje
                log.error("kupuje");

                if (transaction.getCurrency() == Currency.PLN) {    //w zł
                    if (users.get().getWallet().getOwnedPLN() >= transaction.getValue()) {
                        log.debug("mam pln");

                        users.get().getWallet().setOwnedPLN(users.get().getWallet().getOwnedPLN() - transaction.getValue()); // pomniejsz saldo
                        log.debug("pomniejszylem saldo");

                        users.get().getWallet().getTransactions().add(transaction);
                        transactionsRepository.save(transaction);
                        usersRepository.save(users.get());
                        model.addAttribute("works", 1);
                        return "form3";

                    } else {    //nie mam pieniedzy
                        model.addAttribute("works", 3);
                        return "form3";
                    }
                } else {    //w usd
                    if (users.get().getWallet().getOwnedUSD() >= transaction.getValue()) {
                        log.debug("mma usd");

                        users.get().getWallet().setOwnedUSD(users.get().getWallet().getOwnedUSD() - transaction.getValue()); // pomniejsz saldo
                        log.debug("pomniejszylem saldo");

                        users.get().getWallet().getTransactions().add(transaction);
                        transactionsRepository.save(transaction);
                        usersRepository.save(users.get());
                        model.addAttribute("works", 1);
                        return "form3";

                    } else {    //nie mam pieniedzy
                        model.addAttribute("works", 3);
                        return "form3";
                    }
                }
            } else {    //sprzedaje
                double currentAmount = 0;

                for (Transactions transactions : users.get().getWallet().getTransactions()) {           //sprawdz czy isteniej i albo dopisz albo dodaj
                    log.debug(transactions.toString());

                    if (transactions.getCoinName().equals(transaction.getCoinName())) {       //sprawdz czy isteniej i albo dopisz albo dodaj

                        if (transactions.getType() == TransactionType.Sell) {
                            log.debug("SELL");

                            currentAmount -= transactions.getAmount();
                        } else {
                            log.debug("BUY");

                            currentAmount += transactions.getAmount();
                        }
                    }
                }

                if (currentAmount >= transaction.getAmount()) {  //jesli mam wiecej niz sprzedaje
                    log.debug("mam coiny");
                    if (transaction.getCurrency() == Currency.PLN) {
                        users.get().getWallet().setOwnedPLN(users.get().getWallet().getOwnedPLN() + transaction.getValue()); // pomniejsz saldo
                    } else {
                        users.get().getWallet().setOwnedUSD(users.get().getWallet().getOwnedUSD() + transaction.getValue()); // powieksz saldo
                    }
                    log.debug("powiekszylem saldo");

                    users.get().getWallet().getTransactions().add(transaction);
                    transactionsRepository.save(transaction);
                    usersRepository.save(users.get());
                    model.addAttribute("works", 1);
                    return "form3";
                } else {
                    log.debug("nie mam coinów");

                    model.addAttribute("works", 3);
                    return "form3";
                }
            }
        }
    }
}
