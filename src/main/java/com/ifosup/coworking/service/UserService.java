package com.ifosup.coworking.service;

import com.ifosup.coworking.domain.Authority;
import com.ifosup.coworking.domain.User;
import com.ifosup.coworking.dto.RegistrationDto;
import com.ifosup.coworking.repository.AuthorityRepository;
import com.ifosup.coworking.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static com.ifosup.coworking.security.AuthoritiesConstants.USER;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserDetailsService.class);

    private final UserRepository userRepository;

    private final AuthorityRepository authorityRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerNewUser(RegistrationDto registrationDto) {
        logger.debug("Trying to register {}", registrationDto);

        Authority authority = authorityRepository.findOne(USER);
        Set<Authority> authorities = new HashSet<>();
        authorities.add(authority);

        User user = new User();
        user.authorities = authorities;
        user.email = registrationDto.email;
        user.passwordHash = passwordEncoder.encode(registrationDto.password);
        user.lastName = registrationDto.lastName;
        user.firstName = registrationDto.firstName;

        logger.debug("Trying to save {}", user);
        return userRepository.save(user);
    }
}
