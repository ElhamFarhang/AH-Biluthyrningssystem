package com.example.ahbiluthyrningssystem.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


//--------------------- Elham - class WebSecurityConfig --------------
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    //  Elham & Wille
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/cars/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/api/v1/addorder").hasRole("USER")
                        .requestMatchers("/api/v1/cancelorder/**").hasRole("USER")
                        .requestMatchers("/api/v1/activeorders").hasRole("USER")
                        .requestMatchers("/api/v1/orders").hasRole("USER")
                        .requestMatchers("/api/v1/updateinfo/**").hasRole("USER")
                        .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .csrf(csrf-> csrf.disable());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User
                .withUsername("admin")
                .password("{noop}admin")
                .roles("ADMIN")
                .build();

        UserDetails Anna = User
                .withUsername("19850101-1234")
                .password("{noop}1234")
                .roles("USER")
                .build();

        UserDetails Erik = User
                .withUsername("19900215-5678")
                .password("{noop}5678")
                .roles("USER")
                .build();

        UserDetails Maria = User
                .withUsername("19751230-9101")
                .password("{noop}9101")
                .roles("USER")
                .build();

        UserDetails Johan = User
                .withUsername("19881122-3456")
                .password("{noop}3456")
                .roles("USER")
                .build();

        UserDetails Elin = User
                .withUsername("19950505-7890")
                .password("{noop}7890")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, Anna, Erik, Maria, Johan, Elin);
    }
}
//--------------------- Elham - class WebSecurityConfig --------------
