package org.example.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Entity
@Data
public abstract class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String priceCurrency;
    private Double priceAmount;
    private Long billNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String notes;
}
