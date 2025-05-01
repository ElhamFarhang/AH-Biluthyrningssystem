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
                        .requestMatchers("/api/v1/cars").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/api/v1/addorder").hasRole("USER")
                        .requestMatchers("/api/v1/cancelorder/**").hasRole("USER")
                        .requestMatchers("/api/v1/activeorders").hasRole("USER")
                        .requestMatchers("/api/v1/orders").hasRole("USER")
                        .requestMatchers("/api/v1/updateinfo/**").hasRole("USER")
                        .requestMatchers("/api/v1/admin/customers").hasRole("ADMIN")
                        .requestMatchers("/api/v1/admin/customer/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/admin/addcustomer").hasRole("ADMIN")
                        .requestMatchers("/api/v1/admin/removecustomer/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/api/v1/admin/allcars").hasRole("ADMIN")
//                        .requestMatchers("/api/v1/admin/addcars").hasRole("ADMIN")
//                        .requestMatchers("/api/v1/admin/updatecar").hasRole("ADMIN")
//                        .requestMatchers("/api/v1/admin/removecar").hasRole("ADMIN")
//                        .requestMatchers("/api/v1/admin/orders").hasRole("ADMIN")
//                        .requestMatchers("/api/v1/admin/removeorders").hasRole("ADMIN")
//                        .requestMatchers("/api/v1/admin/removeorders-beforedate/**").hasRole("ADMIN")
//                        .requestMatchers("/api/v1/admin/statistics/**").hasRole("ADMIN")
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
                .withUsername("Anna")
                .password("{noop}1234")
                .roles("USER")
                .build();

        UserDetails Erik = User
                .withUsername("Erik")
                .password("{noop}5678")
                .roles("USER")
                .build();

        UserDetails Maria = User
                .withUsername("Maria")
                .password("{noop}9101")
                .roles("USER")
                .build();

        UserDetails Johan = User
                .withUsername("Johan")
                .password("{noop}3456")
                .roles("USER")
                .build();

        UserDetails Elin = User
                .withUsername("Elin")
                .password("{noop}7890")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, Anna, Erik, Maria, Johan, Elin);
    }
}
//--------------------- Elham - class WebSecurityConfig --------------
