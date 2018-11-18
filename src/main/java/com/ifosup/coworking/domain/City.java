package com.ifosup.coworking.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class City{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotNull
    @Column(length = 30)
    public String name;

    @NotNull
    public int cpCity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCpCity() {
        return cpCity;
    }

    public void setCpCity(int cpCity) {
        this.cpCity = cpCity;
    }
}
