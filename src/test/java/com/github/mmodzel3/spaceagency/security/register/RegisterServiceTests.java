package com.github.mmodzel3.spaceagency.security.register;

import com.github.mmodzel3.spaceagency.user.User;
import com.github.mmodzel3.spaceagency.user.UserExists;
import com.github.mmodzel3.spaceagency.user.UserService;
import com.github.mmodzel3.spaceagency.user.UserTestsAbstract;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class RegisterServiceTests extends UserTestsAbstract {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private UserService userService;

    @Test
    void whenRegisterNotExistingAccountThenAccountIsCreated() throws UserExists {
        registerService.register(TEST_USERNAME, TEST_PASSWORD, TEST_ROLE);

        Optional<User> possibleUser = userService.findUser(TEST_USERNAME);
        assertTrue(possibleUser.isPresent());
    }

    @Test
    void whenRegisterDuplicatedAccountThenAccountIsNotCreated() throws UserExists {
        createTestUser();

        assertThrows(UserExists.class, () ->
                registerService.register(TEST_USERNAME, TEST_PASSWORD, TEST_ROLE));
    }
}
