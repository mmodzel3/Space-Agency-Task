package com.github.mmodzel3.spaceagency.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UserServiceTests extends UserTestsAbstract {
    private static final long ONE_USER = 1;
    @Autowired
    private UserService userService;

    @Test
    void whenAddUserThenItIsAdded() throws UserExistsException {
        User user = User.builder()
                .username(TEST_USERNAME)
                .password(TEST_PASSWORD)
                .role(TEST_ROLE)
                .build();

        userService.addUser(user);

        assertEquals(ONE_USER, userRepository.count());
    }

    @Test
    void whenAddExistingUserThenItIsNotAdded() throws UserExistsException {
        createTestUser();
        
        User user = User.builder()
                .username(TEST_USERNAME)
                .password(TEST_PASSWORD)
                .role(TEST_ROLE)
                .build();

        assertThrows(UserExistsException.class, () -> userService.addUser(user));
    }
}
