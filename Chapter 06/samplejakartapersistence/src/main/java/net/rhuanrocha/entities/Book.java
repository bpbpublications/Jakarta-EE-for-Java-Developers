package net.rhuanrocha.entities;


import jakarta.persistence.*;

import java.util.Set;

@NamedEntityGraphs({
        @NamedEntityGraph(name="bookEntityGraphOne", attributeNodes={
                @NamedAttributeNode("title"),
                @NamedAttributeNode("authors")
        }),
        @NamedEntityGraph(name="bookEntityGraphTwo", attributeNodes={
                @NamedAttributeNode("authors")
        })
})
@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    //@Basic(fetch=FetchType.LAZY)
    private String title;

    @Column
    //@Basic(fetch=FetchType.LAZY)
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Author> authors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }
}
