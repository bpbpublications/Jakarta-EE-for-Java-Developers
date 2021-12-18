package net.rhuanrocha.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Min(value = 3)
    @Max(value = 15)
    @Column
    private String name;

    @Email
    @NotBlank
    @Column
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
