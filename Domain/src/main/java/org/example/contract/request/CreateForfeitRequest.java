package org.example.contract.request;

import lombok.Data;
@Data
public class CreateForfeitRequest {
    private Long vehiclesId;
    private Long partitionId;
    private String priceCurrency;
    private Double priceAmount;
    private String reason;
}
