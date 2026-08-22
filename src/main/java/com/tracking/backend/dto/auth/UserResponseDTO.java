package com.tracking.backend.dto.auth;

public record UserResponseDTO(
        Long id,
        String email,
        String name
) {
}
