package net.rhuanrocha.business;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import net.rhuanrocha.entities.Author;
import net.rhuanrocha.entities.Book;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class BookBusiness {

    private EntityManagerFactory entityManagerFactory;

    @PostConstruct
    public void init(){
        entityManagerFactory = Persistence
                .createEntityManagerFactory("jakartaee-unit");
    }

    public List<Book> findAll(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityGraph<Book> entityGraph = (EntityGraph<Book>) entityManager.getEntityGraph("bookEntityGraphTwo");

        List<Book> books = entityManager
                .createQuery("select b from Book b")
                .setHint("jakarta.persistence.fetchgraph", entityGraph)
                .getResultList();
        entityManager.close();
        return books;
    }

    public List<Book> findByAuthorName(String name){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Book> books = entityManager
                .createQuery("select b from Book b join fetch b.authors a where a.name=:name")
                .setParameter("name",name)
                .getResultList();
        entityManager.close();
        return books;
    }

    public Book save (Book book){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        //Author needs to be attached to session
        if(book.getAuthors() != null){
            Set<Author> authors = book
                    .getAuthors()
                    .stream()
                    .map(author->entityManager.find(Author.class,author.getId()))
                    .collect(Collectors.toSet());
            book.setAuthors(authors);
        }

        entityManager.persist(book);

        entityManager.getTransaction().commit();
        entityManager.close();
        return book;
    }
}
