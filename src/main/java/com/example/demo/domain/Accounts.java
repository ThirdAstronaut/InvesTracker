package com.example.demo.domain;

import com.example.demo.domain.enumeration.AccountStatus;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Accounts.
 */
@Entity
@Table(name = "accounts")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Accounts implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "creation_date", nullable = false)
    private Instant creationDate;

    @NotNull
    @Column(name = "login", nullable = false)
    private String login;

    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "account_status", nullable = false)
    private AccountStatus accountStatus;

    @Column(name = "send_report")
    private Boolean sendReport;

    @OneToOne    @JoinColumn(unique = true)
    private Wallets wallet;

    @OneToMany(mappedBy = "accounts")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Reports> reports = new HashSet<>();

     public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Instant getCreationDate() {
        return creationDate;
    }

    public Accounts creationDate(Instant creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public String getLogin() {
        return login;
    }

    public Accounts login(String login) {
        this.login = login;
        return this;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public Accounts password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public Accounts accountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
        return this;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Boolean isSendReport() {
        return sendReport;
    }

    public Accounts sendReport(Boolean sendReport) {
        this.sendReport = sendReport;
        return this;
    }

    public void setSendReport(Boolean sendReport) {
        this.sendReport = sendReport;
    }

    public Wallets getWallet() {
        return wallet;
    }

    public Accounts wallet(Wallets wallets) {
        this.wallet = wallets;
        return this;
    }

    public void setWallet(Wallets wallets) {
        this.wallet = wallets;
    }

    public Set<Reports> getReports() {
        return reports;
    }

    public Accounts reports(Set<Reports> reports) {
        this.reports = reports;
        return this;
    }

    public Accounts addReport(Reports reports) {
        this.reports.add(reports);
        reports.setAccounts(this);
        return this;
    }

    public Accounts removeReport(Reports reports) {
        this.reports.remove(reports);
        reports.setAccounts(null);
        return this;
    }

    public void setReports(Set<Reports> reports) {
        this.reports = reports;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Accounts accounts = (Accounts) o;
        if (accounts.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), accounts.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Accounts{" +
            "id=" + getId() +
            ", creationDate='" + getCreationDate() + "'" +
            ", login='" + getLogin() + "'" +
            ", password='" + getPassword() + "'" +
            ", accountStatus='" + getAccountStatus() + "'" +
            ", sendReport='" + isSendReport() + "'" +
            "}";
    }
}
