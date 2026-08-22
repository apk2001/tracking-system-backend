package com.tracking.backend.security;

import com.tracking.backend.entity.RefreshToken;
import com.tracking.backend.entity.User;
import com.tracking.backend.exception.InvalidRefreshTokenException;
import com.tracking.backend.repository.RefreshTokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.HexFormat;
import java.util.List;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final Duration refreshTokenTtl;
    private final SecureRandom secureRandom = new SecureRandom();

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository,
                                @Value("${app.jwt.refresh-token-ttl-days}") long refreshTokenTtlDays) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.refreshTokenTtl = Duration.ofDays(refreshTokenTtlDays);
    }

    public String issue(User user, HttpServletRequest request) {
        String rawToken = generateRawToken();
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUserId(user.getId());
        refreshToken.setTokenHash(hash(rawToken));
        refreshToken.setExpiresAt(Instant.now().plus(refreshTokenTtl));
        refreshToken.setUserAgent(request.getHeader("User-Agent"));
        refreshToken.setIpAddress(request.getRemoteAddr());
        refreshTokenRepository.save(refreshToken);
        return rawToken;
    }

    @Transactional
    public RotationResult rotate(String rawToken, HttpServletRequest request) {
        RefreshToken existing = refreshTokenRepository.findByTokenHash(hash(rawToken))
                .orElseThrow(() -> new InvalidRefreshTokenException("Unknown refresh token"));

        if (existing.getRevokedAt() != null) {
            revokeAllForUser(existing.getUserId());
            throw new InvalidRefreshTokenException("Refresh token reuse detected; all sessions revoked");
        }

        if (existing.getExpiresAt().isBefore(Instant.now())) {
            throw new InvalidRefreshTokenException("Refresh token expired");
        }

        String newRawToken = generateRawToken();
        RefreshToken replacement = new RefreshToken();
        replacement.setUserId(existing.getUserId());
        replacement.setTokenHash(hash(newRawToken));
        replacement.setExpiresAt(Instant.now().plus(refreshTokenTtl));
        replacement.setUserAgent(request.getHeader("User-Agent"));
        replacement.setIpAddress(request.getRemoteAddr());
        refreshTokenRepository.save(replacement);

        existing.setRevokedAt(Instant.now());
        existing.setReplacedById(replacement.getId());
        refreshTokenRepository.save(existing);

        return new RotationResult(existing.getUserId(), newRawToken);
    }

    public void revoke(String rawToken) {
        refreshTokenRepository.findByTokenHash(hash(rawToken))
                .ifPresent(token -> {
                    token.setRevokedAt(Instant.now());
                    refreshTokenRepository.save(token);
                });
    }

    @Transactional
    public void revokeAllForUser(Long userId) {
        List<RefreshToken> activeTokens = refreshTokenRepository.findAllByUserIdAndRevokedAtIsNull(userId);
        Instant now = Instant.now();
        activeTokens.forEach(token -> token.setRevokedAt(now));
        refreshTokenRepository.saveAll(activeTokens);
    }

    public Duration refreshTokenTtl() {
        return refreshTokenTtl;
    }

    private String generateRawToken() {
        byte[] bytes = new byte[32];
        secureRandom.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    private String hash(String rawToken) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return HexFormat.of().formatHex(digest.digest(rawToken.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 not available", e);
        }
    }

    public record RotationResult(Long userId, String rawToken) {
    }
}
