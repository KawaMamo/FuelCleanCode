package org.example.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class PartitionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToOne
    private MaterialEntity material;
    private Integer amount;
    private Integer correctedAmount;
    private String priceCurrency;
    private Double priceAmount;
    @OneToOne
    private GasStationEntity gasStation;
    private String notes;
    private String extraNotes;
    @OneToOne
    private DocumentEntity document;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @OneToOne
    private TransportationEntity transportation;

}
