package com.ifosup.coworking.domain;

import javax.persistence.*;

@Entity
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(length = 30)
    public String nameBuilding;

    public String addressBuilding;

  @ManyToOne
    public City city;

    public void setId(Long id) {
        this.id = id;
    }

    public void setNameBuilding(String nameBuilding) {
        this.nameBuilding = nameBuilding;
    }

    public void setAddressBuilding(String addressBuilding) {
        this.addressBuilding = addressBuilding;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
