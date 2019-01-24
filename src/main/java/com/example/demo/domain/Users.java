package com.example.demo.domain;

import com.example.demo.domain.enumeration.AccountStatus;
import com.example.demo.domain.enumeration.Sex;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A Users.
 */
@Entity
@Table(name = "users")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Users implements Serializable {

   // private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "surname", nullable = false)
    private String surname;

    @NotNull
    @Column(name = "birth_date", nullable = false)
    private Instant birthDate;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sex", nullable = false)
    private Sex sex;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "account_status", nullable = false)
    private AccountStatus accountStatus;

    @Column(name = "age")
    private Integer age;

    @OneToOne(cascade = CascadeType.ALL)    @JoinColumn(unique = true)
    private Wallets wallet;

   public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Users name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public Users surname(String surname) {
        this.surname = surname;
        return this;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Instant getBirthDate() {
        return birthDate;
    }

    public Users birthDate(Instant birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public void setBirthDate(Instant birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public Users email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Sex getSex() {
        return sex;
    }

    public Users sex(Sex sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public Users accountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
        return this;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Integer getAge() {
        return age;
    }

    public Users age(Integer age) {
        this.age = age;
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Wallets getWallet() {
        return wallet;
    }

    public Users wallet(Wallets wallets) {
        this.wallet = wallets;
        return this;
    }

    public void setWallet(Wallets wallets) {
        this.wallet = wallets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Users users = (Users) o;
        if (users.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), users.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Users{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", surname='" + getSurname() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", email='" + getEmail() + "'" +
            ", sex='" + getSex() + "'" +
            ", accountStatus='" + getAccountStatus() + "'" +
            ", age=" + getAge() +
            "}";
    }
}
