package com.github.mmodzel3.spaceagency.user;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findUser(String username) {
        return userRepository.findByUsername(username);
    }

    public void addUser(User user) throws UserExistsException {
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new UserExistsException();
        }
    }
}
