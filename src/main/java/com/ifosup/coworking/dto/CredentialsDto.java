package com.ifosup.coworking.dto;

//import javax.validation.constraints.NotBlank;

public class CredentialsDto {

    //    @NotBlank
    public String email;

    //    @NotBlank
    public String password;

    public Boolean rememberMe;

    @Override
    public String toString() {
        return "CredentialsDto{" +
            "email='" + email + '\'' +
            ", rememberMe=" + rememberMe +
            '}';
    }
}
