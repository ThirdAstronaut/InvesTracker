package com.example.demo.domain;
/*
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "cryptocurrencies")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Cryptocurrencies implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "avg_price", nullable = false)
    private Double avgPrice;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "shortcut")
    private String shortcut;

    @Column(name = "market_capitalization")
    private Long marketCapitalization;

    @Column(name = "volume_24_hour")
    private Double volume24Hour;

    @Column(name = "price_change_1_hour")
    private Double priceChange1Hour;

    @NotNull
    @Column(name = "price_change_24_hours", nullable = false)
    private Double priceChange24Hours;

    @Column(name = "price_change_7_days")
    private Double priceChange7Days;

    @Column(name = "jhi_date")
    private Instant date;

    @Column(name = "ranking_position")
    private Integer rankingPosition;

    @OneToMany(mappedBy = "cryptocurrencies")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Transactions> transactions = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAvgPrice() {
        return avgPrice;
    }

    public Cryptocurrencies avgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
        return this;
    }

    public void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getName() {
        return name;
    }

    public Cryptocurrencies name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortcut() {
        return shortcut;
    }

    public Cryptocurrencies shortcut(String shortcut) {
        this.shortcut = shortcut;
        return this;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }

    public Long getMarketCapitalization() {
        return marketCapitalization;
    }

    public Cryptocurrencies marketCapitalization(Long marketCapitalization) {
        this.marketCapitalization = marketCapitalization;
        return this;
    }

    public void setMarketCapitalization(Long marketCapitalization) {
        this.marketCapitalization = marketCapitalization;
    }

    public Double getVolume24Hour() {
        return volume24Hour;
    }

    public Cryptocurrencies volume24Hour(Double volume24Hour) {
        this.volume24Hour = volume24Hour;
        return this;
    }

    public void setVolume24Hour(Double volume24Hour) {
        this.volume24Hour = volume24Hour;
    }

    public Double getPriceChange1Hour() {
        return priceChange1Hour;
    }

    public Cryptocurrencies priceChange1Hour(Double priceChange1Hour) {
        this.priceChange1Hour = priceChange1Hour;
        return this;
    }

    public void setPriceChange1Hour(Double priceChange1Hour) {
        this.priceChange1Hour = priceChange1Hour;
    }

    public Double getPriceChange24Hours() {
        return priceChange24Hours;
    }

    public Cryptocurrencies priceChange24Hours(Double priceChange24Hours) {
        this.priceChange24Hours = priceChange24Hours;
        return this;
    }

    public void setPriceChange24Hours(Double priceChange24Hours) {
        this.priceChange24Hours = priceChange24Hours;
    }

    public Double getPriceChange7Days() {
        return priceChange7Days;
    }

    public Cryptocurrencies priceChange7Days(Double priceChange7Days) {
        this.priceChange7Days = priceChange7Days;
        return this;
    }

    public void setPriceChange7Days(Double priceChange7Days) {
        this.priceChange7Days = priceChange7Days;
    }

    public Instant getDate() {
        return date;
    }

    public Cryptocurrencies date(Instant date) {
        this.date = date;
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Integer getRankingPosition() {
        return rankingPosition;
    }

    public Cryptocurrencies rankingPosition(Integer rankingPosition) {
        this.rankingPosition = rankingPosition;
        return this;
    }

    public void setRankingPosition(Integer rankingPosition) {
        this.rankingPosition = rankingPosition;
    }

    public Set<Transactions> getTransactions() {
        return transactions;
    }

    public Cryptocurrencies transactions(Set<Transactions> transactions) {
        this.transactions = transactions;
        return this;
    }

    public Cryptocurrencies addTransaction(Transactions transactions) {
        this.transactions.add(transactions);
        transactions.setCryptocurrencies(this);
        return this;
    }

    public Cryptocurrencies removeTransaction(Transactions transactions) {
        this.transactions.remove(transactions);
        transactions.setCryptocurrencies(null);
        return this;
    }

    public void setTransactions(Set<Transactions> transactions) {
        this.transactions = transactions;
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
        Cryptocurrencies cryptocurrencies = (Cryptocurrencies) o;
        if (cryptocurrencies.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cryptocurrencies.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Cryptocurrencies{" +
            "id=" + getId() +
            ", avgPrice=" + getAvgPrice() +
            ", name='" + getName() + "'" +
            ", shortcut='" + getShortcut() + "'" +
            ", marketCapitalization=" + getMarketCapitalization() +
            ", volume24Hour=" + getVolume24Hour() +
            ", priceChange1Hour=" + getPriceChange1Hour() +
            ", priceChange24Hours=" + getPriceChange24Hours() +
            ", priceChange7Days=" + getPriceChange7Days() +
            ", date='" + getDate() + "'" +
            ", rankingPosition=" + getRankingPosition() +
            "}";
    }
}
*/