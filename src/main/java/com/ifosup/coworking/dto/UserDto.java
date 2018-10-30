package com.ifosup.coworking.dto;

import com.ifosup.coworking.domain.Role;
import com.ifosup.coworking.domain.User;

import java.util.Set;

public class UserDto {

    public Long id;

    public String email;

    public String lastName;

    public String firstName;

    public Set<Role> roles;

    public UserDto(User user) {
        id = user.id;
        email = user.email;
        lastName = user.lastName;
        firstName = user.firstName;
        roles = user.roles;
    }
}
