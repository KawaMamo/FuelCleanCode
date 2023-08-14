package org.example.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
public class TransferMaterialsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToOne
    private GasStationEntity source;
    @OneToOne
    private GasStationEntity destination;
    @OneToOne
    private MaterialEntity material;
    private Long amount;
    private String priceCurrency;
    private Double priceAmount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
