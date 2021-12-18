package net.rhuanrocha.stateless;

import net.rhuanrocha.entity.Purchase;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Stateless
public class PurchaseBusiness implements Serializable {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Purchase> findAll(){
        return entityManager
                .createQuery("select p from Purchase p join fetch p.products", Purchase.class)
                .getResultList();
    }


    public void save(Purchase purchase){
        entityManager.persist(purchase);
    }

    public Optional<Purchase> findById(Long id){
        return Optional.ofNullable(entityManager.find(Purchase.class,id));
    }
}
