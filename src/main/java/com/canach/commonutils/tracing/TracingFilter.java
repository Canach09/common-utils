package com.canach.commonutils.tracing;


import io.opentelemetry.api.trace.Span;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@Order(1)
@Slf4j
public class TracingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // Extraer correlationId del header
        final var correlationId = httpRequest.getHeader("correlationId");
        if (correlationId != null) {
            MDC.put("correlationId", correlationId);
        }

        // Extraer userId del token
        final var tenantId = extractUserIdFromToken(httpRequest);
        if (tenantId != null) {
            MDC.put("tenantId", tenantId);
        }

        // Agregar también al span de OpenTelemetry si existe
        addToCurrentSpan(correlationId, tenantId);

        try {
            chain.doFilter(request, response);
        } finally {
            // Limpiar todo el MDC
            MDC.clear();
        }
    }

    private String extractUserIdFromToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            try {
                String token = authHeader.substring(7);
                // Decodifica tu JWT aquí
                return decodeUserIdFromJWT(token);
            } catch (Exception e) {
                log.debug("Error extracting tenantId from token", e);
            }
        }
        return null;
    }

    private void addToCurrentSpan(String correlationId, String userId) {
        try {
            Span currentSpan = Span.current();
            if (currentSpan.getSpanContext().isValid()) {
                if (correlationId != null) {
                    currentSpan.setAttribute("correlation", correlationId);
                }
                if (userId != null) {
                    currentSpan.setAttribute("tenant", userId);
                }
            }
        } catch (Exception e) {
            log.debug("Error adding attributes to span", e);
        }
    }

    private String decodeUserIdFromJWT(String token) {
        // Implementa tu decodificación JWT
        return "user123"; // placeholder
    }
}