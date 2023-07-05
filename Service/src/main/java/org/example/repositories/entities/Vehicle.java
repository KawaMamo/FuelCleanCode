package org.example.repositories.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String plateNumber;
    @OneToOne
    private TrafficCenterEntity trafficCenter;
    private Integer size;
    @OneToOne
    private OfficeEntity office;
    @OneToOne
    private PersonEntity driver;
    private LocalDateTime createdAt;

}
