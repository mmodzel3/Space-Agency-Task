package com.github.mmodzel3.spaceagency.security;

import com.github.mmodzel3.spaceagency.user.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationProvider authenticationProvider;

    private static final RequestMatcher MANAGER_PROTECTED_URLS = new OrRequestMatcher(
            new AntPathRequestMatcher("/api/admin/**")
    );

    private static final RequestMatcher CUSTOMER_PROTECTED_URLS = new OrRequestMatcher(
            new AntPathRequestMatcher("/api/**")
    );

    WebSecurityConfig(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .requestMatchers(MANAGER_PROTECTED_URLS).hasRole(UserRole.MANAGER.toString())
                .and()
                .authorizeRequests()
                .requestMatchers(CUSTOMER_PROTECTED_URLS).hasRole(UserRole.CUSTOMER.toString())
                .and()
                .authenticationProvider(authenticationProvider)
                .httpBasic();
    }

    @Bean
    AuthenticationEntryPoint forbiddenEntryPoint() {
        return new HttpStatusEntryPoint(HttpStatus.FORBIDDEN);
    }
}
