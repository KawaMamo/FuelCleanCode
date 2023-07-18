package org.example.contract.request;

import lombok.Data;
import org.example.model.Money;

@Data
public class CreateTransferMaterialRequest {
    private Long sourceId;
    private Long destinationId;
    private Long materialId;
    private Long amount;
    private Money price;
}
