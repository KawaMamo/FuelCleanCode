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
    private Long materialId;
    private Integer amount;
    private Integer correctedAmount;
    private String priceCurrency;
    private Double priceAmount;
    private Long gasStationId;
    private String notes;
    private String extraNotes;
    private Long documentId;
    private LocalDateTime createdAt;
    private Long transportationId;

}
