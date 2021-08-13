package com.github.mmodzel3.spaceagency;

import com.github.mmodzel3.spaceagency.security.register.RegisterService;
import com.github.mmodzel3.spaceagency.user.UserExists;
import com.github.mmodzel3.spaceagency.user.UserRole;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class InitialUsersLoader implements ApplicationRunner {

    private static final String MANAGER_USERNAME = "manager";
    private static final String MANAGER_PASSWORD = "manager";

    private static final String CUSTOMER_USERNAME = "customer";
    private static final String CUSTOMER_PASSWORD = "customer";

    private final RegisterService registerService;

    public InitialUsersLoader(RegisterService registerService) {
        this.registerService = registerService;
    }

    @Override
    public void run(ApplicationArguments args) {
        try {
            registerService.register(MANAGER_USERNAME, MANAGER_PASSWORD, UserRole.MANAGER);
            registerService.register(CUSTOMER_USERNAME, CUSTOMER_PASSWORD, UserRole.CUSTOMER);
        } catch (UserExists ignored) {

        }
    }
}
