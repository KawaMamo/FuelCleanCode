package org.example.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
public class TransferMaterialsEntity extends TransportationReasonEntity {

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
