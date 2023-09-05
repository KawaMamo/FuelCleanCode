package org.example.contract.request.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransLineRequest {
    private Long sourceId;
    private Long destinationId;
    private String feeCurrency;
    private Double feeAmount;
}
