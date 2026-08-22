package com.tracking.backend.entity;

import com.tracking.backend.entity.enums.FoodType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "food_logs")
@Getter
@Setter
public class FoodLog extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "food_type", nullable = false, length = 20)
    private FoodType foodType;

    @Column(nullable = false, length = 100)
    private String description;

    @Column(name = "eaten_at", nullable = false)
    private Instant eatenAt;
}
