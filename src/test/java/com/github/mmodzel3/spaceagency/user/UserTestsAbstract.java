package com.github.mmodzel3.spaceagency.user;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public abstract class UserTestsAbstract {
    protected final static String TEST_USERNAME = "username";
    protected final static String TEST_PASSWORD = "password";
    protected final static UserRole TEST_ROLE = UserRole.CUSTOMER;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void tearDown() {
        userRepository.deleteAll();
    }

    protected void createTestUser() {
        User user = User.builder()
                .username(TEST_USERNAME)
                .password(TEST_PASSWORD)
                .role(TEST_ROLE)
                .build();

        userRepository.save(user);
    }
}
