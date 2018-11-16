package com.ifosup.coworking.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(length = 30)
    public String nameCity;

    public int cpCity;

    @OneToMany(mappedBy = "city")
    @JsonIgnore
    public Set<Building> buildings = new HashSet<>();

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
