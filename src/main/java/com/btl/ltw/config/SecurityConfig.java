package com.btl.ltw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 🔐 Encode password
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 🔒 Config security
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth

                        // ✅ Public routes
                        .requestMatchers("/", "/home", "/products", "/products/**",
                                "/auth/**", "/login", "/register",
                                "/css/**", "/js/**", "/images/**")
                        .permitAll()

                        // 👑 Admin only
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        // 👨‍💼 Employee + Admin
                        .requestMatchers("/employee/**").hasAnyRole("ADMIN", "EMPLOYEE")

                        // 👤 Customer
                        .requestMatchers("/cart/**", "/orders/**").hasRole("CUSTOMER")

                        // 🔐 Còn lại phải login
                        .anyRequest().authenticated())

                // 🔑 Login form
                .formLogin(form -> form
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/home", true)
                        .permitAll())

                // 🚪 Logout
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/auth/login"))

                // ⚠️ Tạm tắt CSRF cho dễ test (sau bật lại)
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}