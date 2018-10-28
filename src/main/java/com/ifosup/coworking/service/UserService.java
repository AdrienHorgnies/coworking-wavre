package com.ifosup.coworking.service;

import com.ifosup.coworking.domain.User;
import com.ifosup.coworking.dto.RegistrationDto;
import com.ifosup.coworking.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerNewUser(RegistrationDto registrationDto) {
        if (emailExists(registrationDto.email)) {
            // todo rest ctrl should ultimately respond with bad request "email address is already in use"
            return null;
        }

        User user = new User();
        user.email = registrationDto.email;
        // todo password should be encoded using Bcrypt
        user.password = registrationDto.password;
        user.lastName = registrationDto.lastName;
        user.firstName = registrationDto.firstName;

        return userRepository.save(user);
    }

    private boolean emailExists(String email) {
        return userRepository.findOneByEmail(email).isPresent();
    }

}
