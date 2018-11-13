package com.ifosup.coworking.security;

import com.ifosup.coworking.domain.User;
import com.ifosup.coworking.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final UserRepository userRepository;

    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String email) {
        logger.debug("Authenticating {}", email);
        String lowercaseEmail = email.toLowerCase(Locale.ENGLISH);
        Optional<User> userFromDatabase = userRepository.findOneWithAuthoritiesByEmail(lowercaseEmail);

        return userFromDatabase.map(user -> {
            List<GrantedAuthority> grantedAuthorities = user.authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.name))
                .collect(Collectors.toList());
            return new org.springframework.security.core.userdetails.User(lowercaseEmail,
                user.passwordHash,
                grantedAuthorities);
        }).orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseEmail + " was not found in the " +
            "database"));
    }
}
