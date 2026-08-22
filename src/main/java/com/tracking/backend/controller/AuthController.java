package com.tracking.backend.controller;

import com.tracking.backend.dto.auth.LoginRequestDTO;
import com.tracking.backend.dto.auth.UserResponseDTO;
import com.tracking.backend.entity.User;
import com.tracking.backend.exception.InvalidRefreshTokenException;
import com.tracking.backend.security.CookieUtil;
import com.tracking.backend.security.JwtService;
import com.tracking.backend.security.RefreshTokenService;
import com.tracking.backend.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final CookieUtil cookieUtil;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    public AuthController(AuthService authService,
                           CookieUtil cookieUtil,
                           JwtService jwtService,
                           RefreshTokenService refreshTokenService) {
        this.authService = authService;
        this.cookieUtil = cookieUtil;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/login")
    public UserResponseDTO login(@Valid @RequestBody LoginRequestDTO request,
                                  HttpServletRequest httpRequest,
                                  HttpServletResponse httpResponse) {
        var tokens = authService.login(request, httpRequest);
        issueCookies(httpResponse, tokens);
        return tokens.user();
    }

    @PostMapping("/refresh")
    public UserResponseDTO refresh(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        String rawRefreshToken = extractCookie(httpRequest, CookieUtil.REFRESH_TOKEN_COOKIE)
                .orElseThrow(() -> new InvalidRefreshTokenException("Missing refresh token"));

        var tokens = authService.refresh(rawRefreshToken, httpRequest);
        issueCookies(httpResponse, tokens);
        return tokens.user();
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        extractCookie(httpRequest, CookieUtil.REFRESH_TOKEN_COOKIE)
                .ifPresent(authService::logout);
        cookieUtil.clearAuthCookies(httpResponse);
    }

    @GetMapping("/me")
    public UserResponseDTO me(@AuthenticationPrincipal User user) {
        return authService.currentUser(user);
    }

    private void issueCookies(HttpServletResponse httpResponse, AuthService.AuthTokens tokens) {
        cookieUtil.addAccessTokenCookie(httpResponse, tokens.accessToken(), jwtService.accessTokenTtl());
        cookieUtil.addRefreshTokenCookie(httpResponse, tokens.refreshToken(), refreshTokenService.refreshTokenTtl());
    }

    private Optional<String> extractCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return Optional.empty();
        }
        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(name))
                .map(Cookie::getValue)
                .findFirst();
    }
}
