package org.example.repositories.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String plateNumber;
    @OneToOne
    private TrafficCenterEntity trafficCenterEntity;
    private Integer size;
    @OneToOne
    private OfficeEntity officeEntity;
    @OneToOne
    private PersonEntity driver;
    private LocalDateTime createdAt;

}
