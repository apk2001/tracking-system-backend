package com.tracking.backend.service;

import com.tracking.backend.dto.auth.LoginRequestDTO;
import com.tracking.backend.dto.auth.RegisterRequestDTO;
import com.tracking.backend.dto.auth.UserResponseDTO;
import com.tracking.backend.entity.User;
import com.tracking.backend.exception.InvalidCredentialsException;
import com.tracking.backend.exception.InvalidRefreshTokenException;
import com.tracking.backend.exception.RegistrationClosedException;
import com.tracking.backend.repository.UserRepository;
import com.tracking.backend.security.JwtService;
import com.tracking.backend.security.RefreshTokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    public AuthService(UserRepository userRepository,
                        PasswordEncoder passwordEncoder,
                        JwtService jwtService,
                        RefreshTokenService refreshTokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
    }

    public boolean registrationOpen() {
        return userRepository.count() == 0;
    }

    public AuthTokens register(RegisterRequestDTO request, HttpServletRequest httpRequest) {
        if (!registrationOpen()) {
            throw new RegistrationClosedException("Registration is closed");
        }

        User user = new User();
        user.setEmail(request.email());
        user.setPasswordHash(passwordEncoder.encode(request.password()));
        user.setName(request.name());
        userRepository.save(user);

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = refreshTokenService.issue(user, httpRequest);
        return new AuthTokens(accessToken, refreshToken, toUserResponse(user));
    }

    public AuthTokens login(LoginRequestDTO request, HttpServletRequest httpRequest) {
        User user = userRepository.findByEmail(request.email())
                .filter(candidate -> passwordEncoder.matches(request.password(), candidate.getPasswordHash()))
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = refreshTokenService.issue(user, httpRequest);
        return new AuthTokens(accessToken, refreshToken, toUserResponse(user));
    }

    public AuthTokens refresh(String rawRefreshToken, HttpServletRequest httpRequest) {
        if (rawRefreshToken == null) {
            throw new InvalidRefreshTokenException("Missing refresh token");
        }

        var rotation = refreshTokenService.rotate(rawRefreshToken, httpRequest);
        User user = userRepository.findById(rotation.userId())
                .orElseThrow(() -> new InvalidRefreshTokenException("User no longer exists"));

        String accessToken = jwtService.generateAccessToken(user);
        return new AuthTokens(accessToken, rotation.rawToken(), toUserResponse(user));
    }

    public void logout(String rawRefreshToken) {
        if (rawRefreshToken != null) {
            refreshTokenService.revoke(rawRefreshToken);
        }
    }

    public UserResponseDTO currentUser(User user) {
        return toUserResponse(user);
    }

    private UserResponseDTO toUserResponse(User user) {
        return new UserResponseDTO(user.getId(), user.getEmail(), user.getName());
    }

    public record AuthTokens(String accessToken, String refreshToken, UserResponseDTO user) {
    }
}
