package com.ifosup.coworking.service;

import com.ifosup.coworking.domain.User;
import com.ifosup.coworking.dto.RegistrationDto;
import com.ifosup.coworking.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserDetailsService.class);

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerNewUser(RegistrationDto registrationDto) {
        logger.debug("Trying to register {}", registrationDto);

        User user = new User();
        user.email = registrationDto.email;
        user.passwordHash = passwordEncoder.encode(registrationDto.password);
        user.lastName = registrationDto.lastName;
        user.firstName = registrationDto.firstName;
        // todo should add role ROLE_USER by default

        logger.debug("Trying to save {}", user);
        return userRepository.save(user);
    }
}
