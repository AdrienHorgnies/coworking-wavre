package com.ifosup.coworking.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class CityDto {

    public Long id;

    @NotBlank
    public String name;

    @NotNull
    public Integer cpCity;

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

    public Integer getCpCity() {
        return cpCity;
    }

    public void setCpCity(Integer cpCity) {
        this.cpCity = cpCity;
    }
}
