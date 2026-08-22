package com.tracking.backend.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class CookieUtil {

    public static final String ACCESS_TOKEN_COOKIE = "access_token";
    public static final String REFRESH_TOKEN_COOKIE = "refresh_token";

    public void addAccessTokenCookie(HttpServletResponse response, String token, Duration maxAge) {
        addCookie(response, ACCESS_TOKEN_COOKIE, token, "/", maxAge);
    }

    public void addRefreshTokenCookie(HttpServletResponse response, String token, Duration maxAge) {
        addCookie(response, REFRESH_TOKEN_COOKIE, token, "/api/auth/refresh", maxAge);
    }

    public void clearAuthCookies(HttpServletResponse response) {
        addCookie(response, ACCESS_TOKEN_COOKIE, "", "/", Duration.ZERO);
        addCookie(response, REFRESH_TOKEN_COOKIE, "", "/api/auth/refresh", Duration.ZERO);
    }

    private void addCookie(HttpServletResponse response, String name, String value, String path, Duration maxAge) {
        ResponseCookie cookie = ResponseCookie.from(name, value)
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .path(path)
                .maxAge(maxAge)
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }
}
