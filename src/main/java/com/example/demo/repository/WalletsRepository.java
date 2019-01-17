package com.example.demo.repository;

import com.example.demo.domain.Wallets;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Wallets entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WalletsRepository extends JpaRepository<Wallets, Long> {

}
