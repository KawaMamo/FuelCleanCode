package org.example.contract.request.create;

import lombok.Data;

@Data
public class CreateTransLineRequest {
    private Long sourceId;
    private Long destinationId;
    private String feeCurrency;
    private Double feeAmount;
}
