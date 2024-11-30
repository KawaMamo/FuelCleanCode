package org.example.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
public class TransLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToOne
    private VehicleEntity vehicle;
    @OneToOne
    private TransLineEntity transLine;
    private String feesCurrency;
    private Double feesAmount;
    @ManyToOne
    @JoinColumn(name = "transportation_id", nullable = false)
    private TransportationReasonEntity transportation;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
