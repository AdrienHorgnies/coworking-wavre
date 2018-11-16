package com.ifosup.coworking.domain;

import javax.persistence.*;

@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(length = 30)
    public String nameCity;

    public int cpCity;

    public void setId(Long id) {
        this.id = id;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public void setCpCity(int cpCity) {
        this.cpCity = cpCity;
    }

    public Long getId() {
        return id;
    }

    public String getNameCity() {
        return nameCity;
    }

    public int getCpCity() {
        return cpCity;
    }
}
