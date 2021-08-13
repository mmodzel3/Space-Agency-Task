package com.github.mmodzel3.spaceagency.security.register;

import com.github.mmodzel3.spaceagency.user.User;
import com.github.mmodzel3.spaceagency.user.UserExists;
import com.github.mmodzel3.spaceagency.user.UserRole;
import com.github.mmodzel3.spaceagency.user.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public RegisterService(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(String username, String password, UserRole userRole) throws UserExists {
        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .role(userRole)
                .build();

        userService.addUser(user);
    }
}
