package com.example.demo.domain;

import com.example.demo.service.CoinMarketService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * A Wallets.
 */
@Slf4j
@Entity
@Table(name = "wallets")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Wallets implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private Double value;

    @Column(name = "owned_pln")
    private Double ownedPLN;

    @Column(name = "owned_usd")
    private Double ownedUSD;

    @Column(name = "exchange_rate")
    private Double exchangeRate;

    @OneToMany//(mappedBy = "wallets")
    @JoinColumn(name = "wallets_id")
    private Set<Transactions> transactions = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public Wallets value(Double value) {
        this.value = value;
        return this;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getOwnedPLN() {
        return ownedPLN;
    }

    public Wallets ownedPLN(Double ownedPLN) {
        this.ownedPLN = ownedPLN;
        return this;
    }

    public void setOwnedPLN(Double ownedPLN) {
        this.ownedPLN = ownedPLN;
    }

    public Double getOwnedUSD() {
        return ownedUSD;
    }

    public Wallets ownedUSD(Double ownedUSD) {
        this.ownedUSD = ownedUSD;
        return this;
    }

    public void setOwnedUSD(Double ownedUSD) {
        this.ownedUSD = ownedUSD;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public Wallets exchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
        return this;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Set<Transactions> getTransactions() {
        return transactions;
    }

    public Wallets transactions(Set<Transactions> transactions) {
        this.transactions = transactions;
        return this;
    }

    public Wallets addTransaction(Transactions transactions) {
        this.transactions.add(transactions);
        transactions.setWallets(this);
        return this;
    }

    public Wallets removeTransaction(Transactions transactions) {
        this.transactions.remove(transactions);
        transactions.setWallets(null);
        return this;
    }

    public void setTransactions(Set<Transactions> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Wallets wallets = (Wallets) o;
        if (wallets.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), wallets.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Wallets{" +
            "id=" + getId() +
            ", value=" + getValue() +
            ", ownedPLN=" + getOwnedPLN() +
            ", ownedUSD=" + getOwnedUSD() +
            ", exchangeRate=" + getExchangeRate() +
            "}";
    }
}
