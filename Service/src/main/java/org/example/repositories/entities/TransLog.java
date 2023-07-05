package org.example.repositories.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
public class TransLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToOne
    private Vehicle vehicle;
    @OneToOne
    private TransLine transLine;
    private String feesCurrency;
    private Double feesAmount;
    @OneToOne
    private TransportationEntity transportation;
    private String notes;
    private LocalDateTime createdAt;
}
