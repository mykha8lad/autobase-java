package com.example.AutoBase.configuration;

import com.example.AutoBase.service.userdetailsservice.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;  // Кастомный UserDetailsService

    @Autowired
    private MyBasicAuthenticationEntryPoint authenticationEntryPoint;  // Кастомная точка входа для аутентификации

    // Бин для шифрования паролей
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Бин для AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    // Конфигурация фильтров безопасности
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Отключаем CSRF, если это необходимо
        http.csrf(csrf -> csrf.disable());

        // Разрешаем доступ ко всем конечным точкам для всех пользователей
        List<Endpoints> endpointsForAll = Endpoints.getEndpointForAllUsers();
        for (var endpoint : endpointsForAll) {
            http.authorizeRequests().requestMatchers(endpoint.getEndPoint()).permitAll();
        }

        // Разрешаем доступ только админам
        List<Endpoints> endpointsForAdmin = Endpoints.getEndpointForAdmin();
        for (var endpoint : endpointsForAdmin) {
            http.authorizeRequests().requestMatchers(endpoint.getEndPoint()).hasRole("ADMIN");
        }

        // Разрешаем доступ для авторизованных пользователей (как для пользователя, так и для админа)
        List<Endpoints> endpointsForAdminAndUser = Endpoints.getEndpointForAdminAndAuthUser();
        for (var endpoint : endpointsForAdminAndUser) {
            http.authorizeRequests().requestMatchers(endpoint.getEndPoint()).hasAnyRole("USER", "ADMIN");
        }

        // Обработка исключений с кастомной точкой входа
        http.exceptionHandling(exceptionHandling ->
                exceptionHandling.authenticationEntryPoint(authenticationEntryPoint));

        // Конфигурация для входа и выхода
        http
                .formLogin(form -> form
                        .loginProcessingUrl("/j_spring_security_check")
                        .loginPage("/login")
                        .defaultSuccessUrl("/userInfo")
                        .failureUrl("/login?error=true")
                        .usernameParameter("name")
                        .passwordParameter("password")
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/logoutSuccessful")
                );

        return http.build();
    }
}
