package org.example.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
public class TransLineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToOne
    private PlaceEntity source;
    @OneToOne
    private PlaceEntity destination;
    private String feeCurrency;
    private Double feeAmount;
    private LocalDateTime createdAt;

}
