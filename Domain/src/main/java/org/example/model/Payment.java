package org.example.model;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public abstract class Payment {
    private Long id;
    private Money amount;
    private Long billNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String notes;
}
