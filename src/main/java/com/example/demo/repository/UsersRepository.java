package com.example.demo.repository;

import com.example.demo.domain.Users;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Users entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UsersRepository extends CrudRepository<Users, Long> {

}
