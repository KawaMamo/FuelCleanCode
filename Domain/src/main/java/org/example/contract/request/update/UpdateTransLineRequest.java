package org.example.contract.request.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTransLineRequest {
    private Long id;
    private Long sourceId;
    private Long destinationId;
    private String feeCurrency;
    private Double feeAmount;
}
