package com.example.rest;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class RequestIdFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestIdFilter.class);
    public static final String REQUEST_ID_HEADER = "X-Request-ID";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String requestId = UUID.randomUUID().toString();
        MDC.put("requestId", requestId);
        request.setAttribute(REQUEST_ID_HEADER, requestId);
        response.setHeader(REQUEST_ID_HEADER, requestId);

        logger.info("Incoming request [{}]: {} {}", requestId, request.getMethod(), request.getRequestURI());

        try {
            filterChain.doFilter(request, response);
        } finally {
            logger.info("Completed request [{}]: status={}", requestId, response.getStatus());
            MDC.clear();
        }
    }
}

