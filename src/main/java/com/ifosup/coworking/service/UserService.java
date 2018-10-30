package com.ifosup.coworking.service;

import com.ifosup.coworking.domain.User;
import com.ifosup.coworking.dto.RegistrationDto;
import com.ifosup.coworking.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Service
public class UserService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(UserDetailsService.class);

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User registerNewUser(RegistrationDto registrationDto) {
        logger.debug("Trying to register {}", registrationDto);
        if (emailExists(registrationDto.email)) {
            // todo rest ctrl should ultimately respond with 400 BAD REQUEST "email address is already in use"
            // todo this create a java.lang.NullPointerException
            return null;
        }

        User user = new User();
        user.email = registrationDto.email;
        user.password = bCryptPasswordEncoder.encode(registrationDto.password);
        user.lastName = registrationDto.lastName;
        user.firstName = registrationDto.firstName;
        user.roles = new HashSet<>();
        // todo should add role ROLE_USER by default
//        user.roles.add(roleRepository.getOne(1L));

        logger.debug("Trying to save {}", user);
        return userRepository.save(user);
    }

    private boolean emailExists(String email) {
        return userRepository.findOneByEmail(email).isPresent();
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findOneByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }
}
