package net.rhuanrocha.entities;
import jakarta.persistence.*;

@Entity
public class Author {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    @Column
    //@Basic(fetch=FetchType.LAZY)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
