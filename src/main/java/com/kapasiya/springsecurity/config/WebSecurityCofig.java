package com.kapasiya.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityCofig
{
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http.authorizeHttpRequests(authorizeHttpRequests -> {
            try {
                authorizeHttpRequests
                        .anyRequest()
                        .authenticated()
                        .and().httpBasic(Customizer.withDefaults());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User
                .withUsername("user")
                .password("{noop}user")
                .roles("USER")
                .build();
        UserDetails admin = User
                .withUsername("admin")
                .password("{noop}admin")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user,admin);
    }

//    @Bean
//    public PasswordEncoder passwordEncoder()
//    {
//        return new NoOpPasswordEncoder();
//    }

}
