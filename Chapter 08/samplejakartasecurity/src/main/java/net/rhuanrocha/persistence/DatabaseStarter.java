package net.rhuanrocha.persistence;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

@Singleton
@Startup
public class DatabaseStarter {


    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Pbkdf2PasswordHash passwordHash;

    @PostConstruct
    public void init(){
        User user = new User();
        user.setUserName("admin");
        user.setPassword(passwordHash.generate("admin123".toCharArray()));
        entityManager.persist(user);

        Group group = new Group();
        group.setGroupName("admin");
        group.setUserName("admin");
        entityManager.persist(group);
    }
}
