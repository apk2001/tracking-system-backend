package com.tracking.backend.dto.weight;

import java.math.BigDecimal;

public record WeightResponseDTO(
        Long id,
        String date,
        BigDecimal weightKg
) {
}
