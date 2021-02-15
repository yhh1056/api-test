package com.sample.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

//    @Bean
//    public UserDetailsService user() {
//        final UserDetails user = User.builder()
//            .username("user")
//            .password(passwordEncoder().encode("1234"))
//            .roles("USER")
//            .build();
//
//        final UserDetails admin = User.builder()
//            .username("admin")
//            .password(passwordEncoder().encode("1234"))
//            .roles("ADMIN")
//            .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
