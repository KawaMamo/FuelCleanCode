package org.example.repositories.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
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
    private GasStation gasStation;
    private String notes;
    private String extraNotes;
    @OneToOne
    private Document document;
    private LocalDateTime createdAt;

}
