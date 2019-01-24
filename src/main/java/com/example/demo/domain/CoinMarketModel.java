package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "COINS")
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder(toBuilder = true)
@ToString
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class CoinMarketModel {

    @Id
    private String id;
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;
    @NotNull
    @Column(name = "symbol", nullable = false)
    private String symbol;
    @NotNull
    @Column(name = "rank", nullable = false)
    private String rank;
    @NotNull
    @Column(name = "price_usd", nullable = false)
    private String price_usd;
    @NotNull
    @Column(name = "price_btc", nullable = false)
    private String price_btc;
    @NotNull
    @Column(name = "_24h_volume_usd", nullable = false)
    @JsonProperty("24h_volume_usd")
    private String _24h_volume_usd;
    @NotNull
    @Column(name = "market_cap_usd", nullable = false)
    private String market_cap_usd;
    @NotNull
    @Column(name = "available_supply", nullable = false)
    private String available_supply;
    @NotNull
    @Column(name = "total_supply", nullable = false)
    private String total_supply;
    @NotNull
    @Column(name = "percent_change_1h", nullable = false)
    private String percent_change_1h;
    @NotNull
    @Column(name = "percent_change_24h", nullable = false)
    private String percent_change_24h;
    @NotNull
    @Column(name = "percent_change_7d", nullable = false)
    private String percent_change_7d;
    @NotNull
    @Column(name = "last_updated", nullable = false)
    private String last_updated;

    @OneToMany
    @JoinColumn(name = "coin_id")
    private Set<Transactions> c_transactions = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPrice_usd() {
        return price_usd;
    }

    public void setPrice_usd(String price_usd) {
        this.price_usd = price_usd;
    }

    public String getPrice_btc() {
        return price_btc;
    }

    public void setPrice_btc(String price_btc) {
        this.price_btc = price_btc;
    }

    public String get_24h_volume_usd() {
        return _24h_volume_usd;
    }

    public void set_24h_volume_usd(String _24h_volume_usd) {
        this._24h_volume_usd = _24h_volume_usd;
    }

    public String getMarket_cap_usd() {
        return market_cap_usd;
    }

    public void setMarket_cap_usd(String market_cap_usd) {
        this.market_cap_usd = market_cap_usd;
    }

    public String getAvailable_supply() {
        return available_supply;
    }

    public void setAvailable_supply(String available_supply) {
        this.available_supply = available_supply;
    }

    public String getTotal_supply() {
        return total_supply;
    }

    public void setTotal_supply(String total_supply) {
        this.total_supply = total_supply;
    }

    public String getPercent_change_1h() {
        return percent_change_1h;
    }

    public void setPercent_change_1h(String percent_change_1h) {
        this.percent_change_1h = percent_change_1h;
    }

    public String getPercent_change_24h() {
        return percent_change_24h;
    }

    public void setPercent_change_24h(String percent_change_24h) {
        this.percent_change_24h = percent_change_24h;
    }

    public String getPercent_change_7d() {
        return percent_change_7d;
    }

    public void setPercent_change_7d(String percent_change_7d) {
        this.percent_change_7d = percent_change_7d;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }
}