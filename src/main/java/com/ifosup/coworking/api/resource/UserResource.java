package com.ifosup.coworking.api.resource;

import com.ifosup.coworking.domain.User;
import com.ifosup.coworking.dto.UserDto;
import com.ifosup.coworking.repository.UserRepository;
import com.ifosup.coworking.security.SecurityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static com.ifosup.coworking.security.AuthoritiesConstants.ADMIN;
import static com.ifosup.coworking.security.AuthoritiesConstants.USER;

@RestController
@RequestMapping("api/users")
public class UserResource {

    private final UserRepository userRepository;

    public UserResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("")
    @Secured(ADMIN)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("self")
    @Secured(USER)
    public ResponseEntity<UserDto> getSelf() {
        String currentUserLogin = SecurityUtils.getCurrentUserLogin();
        Optional<User> optionalUser = userRepository.findOneByEmail(currentUserLogin);

        if (!optionalUser.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(new UserDto(optionalUser.get()));
    }
}
