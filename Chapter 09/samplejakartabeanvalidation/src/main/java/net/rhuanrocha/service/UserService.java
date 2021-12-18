package net.rhuanrocha.service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.constraints.NotNull;
import net.rhuanrocha.entity.User;

@Stateless
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(@NotNull User user){
        entityManager.persist(user);
    }
}
