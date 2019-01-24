package com.example.demo.validators;

import com.example.demo.domain.Transactions;
import com.example.demo.service.CoinMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TransactionValidator implements Validator {

    private final CoinMarketService coinMarketService;

    @Autowired
    public TransactionValidator(CoinMarketService coinMarketService) {
        this.coinMarketService = coinMarketService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Transactions.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Transactions transaction = (Transactions) obj;
        if(!coinMarketService.coinExistByName(transaction.getCoinName())){
            errors.rejectValue("coinName", "coinName.empty");
        }

        if (checkInputString(transaction.getCoinName())) {
            errors.rejectValue("coinName", "coinName.empty");
        }

        if (checkInputNumber(transaction.getAmount())) {
            errors.reject("amount", "amount.invalid");
        }

        if (checkInputNumber(transaction.getPrice())) {
            errors.reject("price", "price.invalid");
        }

        transaction.setValue(transaction.getPrice() * transaction.getAmount());

    }

    /**
     * @param price product price
     * @return boolean: is price empty or is it a negative number
     */
    private boolean checkInputNumber(Double price) {

        return price == null ||price < 0;

    }
    /**
     * @param input string: input text
     * @return boolean: is input empty or is its length equal to 0
     */
    private boolean checkInputString(String input) {
        return (input == null || input.trim().length() == 0);
    }
}