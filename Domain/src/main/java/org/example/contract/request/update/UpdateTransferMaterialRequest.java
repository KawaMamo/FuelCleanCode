package org.example.contract.request.update;

import lombok.Data;
import org.example.model.Money;
@Data
public class UpdateTransferMaterialRequest {
    private Long id;
    private Long sourceId;
    private Long destinationId;
    private Long materialId;
    private Long amount;
    private Money price;
}
