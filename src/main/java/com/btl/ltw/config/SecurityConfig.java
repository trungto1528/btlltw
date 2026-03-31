package com.btl.ltw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.SavedRequest;

import jakarta.servlet.http.HttpSession;

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
                        .requestMatchers(
                                        "/",
                                        "/home",
                                        "/products",
                                        "/products/**", // Đường dẫn khớp với   // @GetMapping("/products/{id}")
                                        "/product/**", // Dự phòng nếu bạn đổi sang số ít
                                        "/auth/**",
                                        "/css/**",
                                        "/js/**",
                                        "/images/**",
                                        "/static/**")
                        .permitAll()

                        // 👑 Admin only
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        // 👨‍💼 Employee + Admin
                        .requestMatchers("/employee/**").hasAnyRole("ADMIN", "EMPLOYEE")

                        .requestMatchers("/cart/**").hasRole("CUSTOMER")

                        // 🔐 Còn lại phải login
                        .anyRequest().authenticated())

                // 🔑 Login form
                .formLogin(form -> form
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/login")
                        .successHandler((request, response, authentication) -> {
                                // 1. Kiểm tra xem Spring Security có đang lưu trang nào bị chặn không (SavedRequest)
                                HttpSession session = request.getSession();
                                SavedRequest savedRequest = (SavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");

                                if (savedRequest != null) {
                                // TRƯỜNG HỢP 1: Bị đá về login (Ví dụ: bấm vào /cart/view khi chưa login)
                                // Spring sẽ tự đưa về trang bị chặn đó.
                                String targetUrl = savedRequest.getRedirectUrl();
                                response.sendRedirect(targetUrl);
                                } else {
                                // TRƯỜNG HỢP 2: Tự bấm nút "Đăng nhập" trên Header
                                // Chúng ta lấy từ ô Hidden Input "targetUrl" trong form login.html
                                String targetUrl = request.getParameter("targetUrl");
                                
                                if (targetUrl != null && !targetUrl.isEmpty()) {
                                        response.sendRedirect(targetUrl);
                                } else {
                                        // Mặc định nếu không có gì cả
                                        response.sendRedirect("/btlltw/home"); 
                                }
                                }
                        })
                        .failureUrl("/auth/login?error=true")
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