package com.ifosup.coworking.dto;

import org.hibernate.validator.constraints.NotBlank;

public class RegistrationDto {

    @NotBlank
    public String email;

    @NotBlank
    public String password;

    @NotBlank
    public String lastName;

    @NotBlank
    public String firstName;

    public Boolean rememberMe;

    @Override
    public String toString() {
        return "RegistrationDto{" +
            "email='" + email + '\'' +
            ", lastName='" + lastName + '\'' +
            ", firstName='" + firstName + '\'' +
            ", rememberMe=" + rememberMe +
            '}';
    }
}
