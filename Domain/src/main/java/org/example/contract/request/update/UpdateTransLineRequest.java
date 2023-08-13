package org.example.contract.request.update;

import lombok.Data;

@Data
public class UpdateTransLineRequest {
    private Long id;
    private Long sourceId;
    private Long destinationId;
    private String feeCurrency;
    private Double feeAmount;
}
