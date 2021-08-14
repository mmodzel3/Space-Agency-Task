package com.github.mmodzel3.spaceagency.security;

import com.github.mmodzel3.spaceagency.user.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
class AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    AuthenticationService(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    UserDetails checkCredentialsAndRetrieveUser(String username, String password) {
        return userService.findUser(username)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
