
package com.laboratorio.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    @Value("${api.key}")
    private String apiKey;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String claveRecibida = request.getHeader("X-API-KEY");

        if (claveRecibida == null ||
                !MessageDigest.isEqual(
                    apiKey.getBytes(StandardCharsets.UTF_8),
                    claveRecibida.getBytes(StandardCharsets.UTF_8))) {

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            response.getWriter().write(
                "{\"error\":\"API Key inválida o ausente\"}"
            );

            return;
        }

        filterChain.doFilter(request, response);
    }
}
