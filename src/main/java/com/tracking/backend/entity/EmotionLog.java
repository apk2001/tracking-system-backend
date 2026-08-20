package com.tracking.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "emotion_logs")
@Getter
@Setter
public class EmotionLog extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String feeling;

    @Column(nullable = false, length = 500)
    private String reason;
}
