package com.ifosup.coworking.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class CityDto {

    public Long id;

    @NotBlank
    public String name;

    @NotNull
    public Integer cpCity;
}
