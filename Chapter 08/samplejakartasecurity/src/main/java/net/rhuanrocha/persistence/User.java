package net.rhuanrocha.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @GeneratedValue
    @Id
    private Long id;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
