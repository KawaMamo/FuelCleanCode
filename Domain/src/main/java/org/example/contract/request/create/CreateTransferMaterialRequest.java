package org.example.contract.request.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.Money;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransferMaterialRequest {
    private Long sourceId;
    private Long destinationId;
    private Long materialId;
    private Long amount;
    private Money price;
}
