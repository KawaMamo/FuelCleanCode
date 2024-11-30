package org.example.model;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class TransferMaterials extends TransportationReason{
    private GasStation source;
    private GasStation destination;
    private Material material;
    private Long amount;
    private Money price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
