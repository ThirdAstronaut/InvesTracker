package com.example.demo.domain.DAO;

import com.example.demo.domain.Users;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class JpaUserDao implements Dao<Users> {

    private EntityManager entityManager;

    // standard constructors

    @Override
    public Optional<Users> get(long id) {
        return Optional.ofNullable(entityManager.find(Users.class, id));
    }

    @Override
    public List<Users> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM users e");
        return query.getResultList();
    }

    @Override
    public void save(Users user) {
        executeInsideTransaction(entityManager -> entityManager.persist(user));
    }

    @Override
    public void update(Users user, String[] params) {
        user.setName(Objects.requireNonNull(params[0], "Name cannot be null"));
        user.setEmail(Objects.requireNonNull(params[1], "Email cannot be null"));
        executeInsideTransaction(entityManager -> entityManager.merge(user));
    }

    @Override
    public void delete(Users user) {
        executeInsideTransaction(entityManager -> entityManager.remove(user));
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
}
