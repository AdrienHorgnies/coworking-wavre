package com.ifosup.coworking;

import com.ifosup.coworking.service.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AuthenticationService authenticationService;

    public SecurityConfiguration(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .httpBasic().and()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/api/users").hasRole("ADMIN")
            .antMatchers(HttpMethod.POST, "/api/register", "/api/login").permitAll()
            .and()
            .logout().permitAll()
            .and()
            .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationService);
    }
}
