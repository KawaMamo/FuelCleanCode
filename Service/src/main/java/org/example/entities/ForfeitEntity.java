package org.example.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.example.model.Money;
import org.example.model.Partition;
import org.example.model.Vehicle;

import java.time.LocalDateTime;
@Entity
@Data
public class ForfeitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToOne
    private VehicleEntity vehicles;
    @OneToOne
    private PartitionEntity partition;
    private String priceCurrency;
    private Double priceAmount;
    private String reason;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
