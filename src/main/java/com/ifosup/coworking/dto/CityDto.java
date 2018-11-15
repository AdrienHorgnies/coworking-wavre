package com.ifosup.coworking.dto;

import javax.persistence.*;

@Entity
public class CityDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(length = 30)
    public String nameCity;

    public int cpCity;
}
