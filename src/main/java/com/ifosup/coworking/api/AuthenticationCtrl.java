package com.ifosup.coworking.api;

import com.ifosup.coworking.dto.RegistrationDto;
import com.ifosup.coworking.dto.UserDto;
import com.ifosup.coworking.service.AuthenticationService;
import com.ifosup.coworking.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
public class AuthenticationCtrl {

    private final UserService userService;

    private final AuthenticationService authenticationService;

    private final Logger logger = LoggerFactory.getLogger(AuthenticationCtrl.class);

    public AuthenticationCtrl(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("register")
    public UserDto register(@RequestBody @Valid RegistrationDto registrationDto) {
        // todo check what a REST endpoint should return
        logger.debug("Trying to register {}", registrationDto);
        return new UserDto(userService.registerNewUser(registrationDto));
    }

    @PostMapping("authenticate")
    public Authentication authenticate(@RequestBody @Valid Authentication authentication) {
        // todo check what a REST endpoint should return
        // todo use a valid parameter, Authentication is an abstract type and cannot be used
        return authenticationService.authenticate(authentication);
    }
}
