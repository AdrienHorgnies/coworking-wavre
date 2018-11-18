package com.ifosup.coworking.dto;

import javax.validation.constraints.NotNull;


public class CityDto {

    public Long id;

    @NotNull
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
