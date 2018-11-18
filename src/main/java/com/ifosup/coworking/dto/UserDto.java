package com.ifosup.coworking.dto;

import com.ifosup.coworking.domain.User;
import org.hibernate.validator.constraints.NotBlank;

public class UserDto {

    @NotBlank
    public String email;

    @NotBlank
    public String lastName;

    @NotBlank
    public String firstName;

    public UserDto() {
        // Empty constructor required by Jackson
    }

    public UserDto(User user) {
        this.email = user.email;
        this.lastName = user.lastName;
        this.firstName = user.firstName;
    }

    @Override
    public String toString() {
        return "UserDto{" +
            "email='" + email + '\'' +
            ", lastName='" + lastName + '\'' +
            ", firstName='" + firstName + '\'' +
            '}';
    }
}
