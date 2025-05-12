package com.example.rest.filter;

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

import static io.spring.training.boot.basedomains.constant.RequestIdValues.REQUEST_ID_HEADER;
import static io.spring.training.boot.basedomains.constant.RequestIdValues.REQUEST_ID_KEY;

@Component
public class RequestIdFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestIdFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String requestId = UUID.randomUUID().toString();
        MDC.put(REQUEST_ID_KEY, requestId);
        request.setAttribute(REQUEST_ID_HEADER, requestId);
        response.setHeader(REQUEST_ID_HEADER, requestId);

        LOGGER.info("Incoming request [{}]: {} {}", requestId, request.getMethod(), request.getRequestURI());

        try {
            filterChain.doFilter(request, response);
        } finally {
            LOGGER.info("Completed request [{}]: status={}", requestId, response.getStatus());
            MDC.clear();
        }
    }
}

