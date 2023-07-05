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
    private Material material;
    @OneToOne
    private TransportationEntity transportation;
    private Integer amount;
    private Integer correctedAmount;
    @OneToOne
    private Money price;
    @OneToOne
    private GasStation gasStation;
    private String notes;
    private String extraNotes;
    @OneToOne
    private Document document;
    private LocalDateTime createdAt;

}
