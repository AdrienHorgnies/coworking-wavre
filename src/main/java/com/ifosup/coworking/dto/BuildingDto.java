package com.ifosup.coworking.dto;

import com.ifosup.coworking.domain.City;

import javax.persistence.*;

@Entity
public class BuildingDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(length = 30)
    public String nameBuilding;

    public String addressBuilding;

  @ManyToOne
    public City city;
}
