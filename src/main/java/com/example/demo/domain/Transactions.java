package com.example.demo.domain;

import com.example.demo.domain.enumeration.Currency;
import com.example.demo.domain.enumeration.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Transactions.
 */
@Entity
@Table(name = "transactions")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Transactions implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "jhi_date", nullable = true)
    private LocalDate date;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "jhi_type", nullable = false)
    private TransactionType type;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false)
    private Currency currency;

    @NotNull
    @Column(name = "price", nullable = false)
    private Double price;

    @NotNull
    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "jhi_value", nullable = true)
    private Double value;

    @ManyToOne  //do wallet
    @JsonIgnoreProperties("transactions")
    private Wallets wallets;

    @ManyToOne
    @JsonIgnoreProperties("c_transactions")
    private CoinMarketModel cryptocurrencies;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Transactions date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public TransactionType getType() {
        return type;
    }

    public Transactions type(TransactionType type) {
        this.type = type;
        return this;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Transactions currency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Double getPrice() {
        return price;
    }

    public Transactions price(Double price) {
        this.price = price;
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAmount() {
        return amount;
    }

    public Transactions amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getValue() {
        return value;
    }

    public Transactions value(Double value) {
        this.value = value;
        return this;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Wallets getWallets() {
        return wallets;
    }

    public Transactions wallets(Wallets wallets) {
        this.wallets = wallets;
        return this;
    }

    public void setWallets(Wallets wallets) {
        this.wallets = wallets;
    }

    public CoinMarketModel getCryptocurrencies() {
        return cryptocurrencies;
    }

    public Transactions cryptocurrencies(CoinMarketModel cryptocurrencies) {
        this.cryptocurrencies = cryptocurrencies;
        return this;
    }

    public void setCryptocurrencies(CoinMarketModel cryptocurrencies) {
        this.cryptocurrencies = cryptocurrencies;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Transactions transactions = (Transactions) o;
        if (transactions.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), transactions.getId());
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Transactions{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", type='" + getType() + "'" +
            ", currency='" + getCurrency() + "'" +
            ", price=" + getPrice() +
            ", amount=" + getAmount() +
            ", value=" + getValue() +
            "}";
    }
}
