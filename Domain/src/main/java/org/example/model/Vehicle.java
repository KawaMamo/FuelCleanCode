package org.example.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Vehicle {
    private Long id;
    private String plateNumber;
    private TrafficCenter trafficCenter;
    private Integer size;
    private Office office;
    private Person driver;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
