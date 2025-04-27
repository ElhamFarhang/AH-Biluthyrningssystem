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

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/v1/cars").hasRole("CUSTOMER")
//                        .requestMatchers("/api/v1/addorder").hasRole("CUSTOMER")
//                        .requestMatchers("/api/v1/cancelorder/**").hasRole("CUSTOMER")
//                        .requestMatchers("/api/v1/activeorders").hasRole("CUSTOMER")
//                        .requestMatchers("/api/v1/orders").hasRole("CUSTOMER")
//                        .requestMatchers("/api/v1/updateinfo/**").hasRole("CUSTOMER")
                        .requestMatchers("/api/v1/admin/customers").hasRole("ADMIN")
//                        .requestMatchers("/api/v1/admin/customer/**").hasRole("ADMIN")
//                        .requestMatchers("/api/v1/admin/addcustomer").hasRole("ADMIN")
//                        .requestMatchers("/api/v1/admin/removecustomer/**").hasRole("ADMIN")
//                        .requestMatchers("/api/v1/admin/cars").hasRole("ADMIN")
//
//
//                        .requestMatchers("/api/v1/admin/customers").hasRole("ADMIN")
//                        .requestMatchers("/api/v1/admin/customers").hasRole("ADMIN")
//                        .requestMatchers("/api/v1/admin/customers").hasRole("ADMIN")
//                        .requestMatchers("/api/v1/admin/customers").hasRole("ADMIN")
//                        .requestMatchers("/api/v1/admin/customers").hasRole("ADMIN")



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

//        UserDetails Anna = User
//                .withUsername("Anna")
//                .password("{noop}1234")
//                .roles("USER")
//                .build();
//
//        UserDetails Erik = User
//                .withUsername("Erik")
//                .password("{noop}5678")
//                .roles("USER")
//                .build();
//
//        UserDetails Maria = User
//                .withUsername("Maria")
//                .password("{noop}9101")
//                .roles("USER")
//                .build();
//
//        UserDetails Johan = User
//                .withUsername("Johan")
//                .password("{noop}3456")
//                .roles("USER")
//                .build();
//
//        UserDetails Elin = User
//                .withUsername("Elin")
//                .password("{noop}7890")
//                .roles("USER")
//                .build();

        return new InMemoryUserDetailsManager(admin);
    }

}
