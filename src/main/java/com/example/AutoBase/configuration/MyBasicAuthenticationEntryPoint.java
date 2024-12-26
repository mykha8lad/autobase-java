package com.example.AutoBase.configuration;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// Кастомная реализация базовой точки входа для аутентификации в Spring Security с использ. Basic Authentication

@Component
public class MyBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    private static final String REALM_NAME = "driver";  // имя области (realm)

    // метод, который вызывается при неудачной попытке аутентификации
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        // заголовок сообщает клиенту, что для доступа к ресурсу требуется аутентификация, используя базовую схему
        response.addHeader("WWW-Authenticate", "Basic realm=\"" + REALM_NAME + "\"");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

    // вызывается после того, как все свойства (или зависимости) бина были установлены
    @Override
    public void afterPropertiesSet() {
        setRealmName(REALM_NAME);
        super.afterPropertiesSet();
    }
}
