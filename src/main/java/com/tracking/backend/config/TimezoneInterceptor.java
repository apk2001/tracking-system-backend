package com.tracking.backend.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.DateTimeException;
import java.time.ZoneId;

public class TimezoneInterceptor implements HandlerInterceptor {

    private final RequestTimeZoneHolder requestTimeZoneHolder;

    public TimezoneInterceptor(RequestTimeZoneHolder requestTimeZoneHolder) {
        this.requestTimeZoneHolder = requestTimeZoneHolder;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String header = request.getHeader("X-Timezone");
        if (header != null && !header.isBlank()) {
            try {
                requestTimeZoneHolder.setZoneId(ZoneId.of(header));
            } catch (DateTimeException ex) {
                requestTimeZoneHolder.setZoneId(RequestTimeZoneHolder.DEFAULT_ZONE);
            }
        }
        return true;
    }
}
