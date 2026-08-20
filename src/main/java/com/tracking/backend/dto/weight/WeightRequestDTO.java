package com.tracking.backend.dto.weight;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record WeightRequestDTO(

        @NotNull
        @DecimalMin(value = "0.1", message = "weightKg must be greater than 0")
        BigDecimal weightKg
) {
}
