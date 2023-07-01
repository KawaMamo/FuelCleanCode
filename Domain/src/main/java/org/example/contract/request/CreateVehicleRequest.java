package org.example.contract.request;

import lombok.Data;
import org.example.model.Office;
import org.example.model.Person;
import org.example.model.TrafficCenter;

import java.time.LocalDateTime;
@Data
public class CreateVehicleRequest {
    private String plateNumber;
    private TrafficCenter trafficCenter;
    private Integer size;
    private Office office;
    private Person driver;
    private LocalDateTime createdAt;
}
