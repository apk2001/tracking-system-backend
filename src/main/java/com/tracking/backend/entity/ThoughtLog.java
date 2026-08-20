package com.tracking.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "thought_logs")
@Getter
@Setter
public class ThoughtLog extends BaseEntity {

    @Column(nullable = false, length = 1500)
    private String content;
}
