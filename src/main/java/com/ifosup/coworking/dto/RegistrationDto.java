package com.ifosup.coworking.dto;

import javax.validation.constraints.NotBlank;

public class RegistrationDto {

    @NotBlank
    public String email;

    @NotBlank
    public String password;

    @NotBlank
    public String lastName;

    @NotBlank
    public String firstName;

}
