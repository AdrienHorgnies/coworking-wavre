package com.ifosup.coworking.api;

import com.ifosup.coworking.domain.User;
import com.ifosup.coworking.dto.RegistrationDto;
import com.ifosup.coworking.service.UserService;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
public class AuthenticationCtrl {

    private final UserService userService;

    public AuthenticationCtrl(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public User register(@RequestBody @Valid RegistrationDto registrationDto) {
        // todo it should return 201 CREATED when successful
        return userService.registerNewUser(registrationDto);
    }

    @PostMapping("authenticate")
    public User authenticate(@Valid UserCredentials credentials) {
        // todo I am not sure I want to use org.springframework.data.authentication.UserCredentials
        // todo it should return 204 NO CONTENT when successful ??? At least is should return something
        return null;
    }
}
