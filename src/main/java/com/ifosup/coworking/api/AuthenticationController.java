package com.ifosup.coworking.api;

import com.ifosup.coworking.dto.CredentialsDto;
import com.ifosup.coworking.dto.RegistrationDto;
import com.ifosup.coworking.repository.UserRepository;
import com.ifosup.coworking.security.jwt.JWTConfigurer;
import com.ifosup.coworking.security.jwt.TokenProvider;
import com.ifosup.coworking.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequestMapping("api")
public class AuthenticationController {

    private final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private final UserService userService;

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final TokenProvider tokenProvider;

    public AuthenticationController(UserService userService, UserRepository userRepository, AuthenticationManager authenticationManager, TokenProvider tokenProvider) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    // todo maybe I need to use "@PostMapping(path = "/register", produces={MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})"
    @PostMapping("register")
    public ResponseEntity register(@RequestBody @Valid RegistrationDto registrationDto) {
        // todo why text plain headers ?
        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);

        // todo use a method and get number from application.yml
        if (registrationDto.password.length() < 8) {
            // todo again, number should come from application.yml
            return new ResponseEntity<>("Password length should be at least 8", HttpStatus.BAD_REQUEST);
        }

        if (userRepository.findOneByEmail(registrationDto.email.toLowerCase()).isPresent()) {
            return new ResponseEntity<>("email already in use", textPlainHeaders, HttpStatus.BAD_REQUEST);
        }

        userService.registerNewUser(registrationDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("authenticate")
    public ResponseEntity authenticate(@RequestBody @Valid CredentialsDto credentialsDto, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(credentialsDto.email, credentialsDto.password);

        try {
            Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            boolean rememberMe = false;
            if (credentialsDto.rememberMe != null) {
                rememberMe = credentialsDto.rememberMe;
            }

            String jwt = tokenProvider.createToken(authentication, rememberMe);
            response.addHeader(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);

            return ResponseEntity.noContent().build();
        } catch (AuthenticationException e) {
            logger.trace("Authentication exception trace: {}", e);
            return new ResponseEntity<>(Collections.singletonMap("AuthenticationException",
                e.getLocalizedMessage()), HttpStatus.UNAUTHORIZED);
        }
    }
}
