package org.example.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReturnedMaterial {
    private Long id;
    private GasStation gasStation;
    private Material material;
    private Long amount;
    private Money price;
    private Money centerPrice;
    private String status;
    private Buyer buyer;
    private String invoiceNo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
