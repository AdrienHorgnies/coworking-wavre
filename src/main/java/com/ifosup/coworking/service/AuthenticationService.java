package com.ifosup.coworking.service;

import com.ifosup.coworking.domain.User;
import com.ifosup.coworking.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements AuthenticationProvider {

    private final UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        logger.debug("Trying to log in with {}", email);

        String password = authentication.getCredentials().toString();

        User user = userRepository.findOneByEmail(email)
            .filter(userWithMaybeSamePassword -> password.equals(userWithMaybeSamePassword.password))
            .orElseThrow(() -> new BadCredentialsException("Authentication failed for " + email));

        return new UsernamePasswordAuthenticationToken(user.email, user.password, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
