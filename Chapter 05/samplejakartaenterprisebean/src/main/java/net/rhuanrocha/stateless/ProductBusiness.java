package net.rhuanrocha.stateless;

import net.rhuanrocha.entity.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Stateless
public class ProductBusiness implements Serializable {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> findAll(){
        return entityManager
                .createQuery("select p from Product p", Product.class)
                .getResultList();
    }

    public void save(Product product){
        entityManager.persist(product);
    }

    public Optional<Product> findById(Long id){
        return Optional.ofNullable(entityManager.find(Product.class,id));
    }
}
