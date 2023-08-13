package org.example.contract.request.create;

import lombok.Data;
import org.example.model.Money;

@Data
public class CreateForfeitRequest {
    private Long vehiclesId;
    private Long partitionId;
    private Money price;
    private String reason;
}
