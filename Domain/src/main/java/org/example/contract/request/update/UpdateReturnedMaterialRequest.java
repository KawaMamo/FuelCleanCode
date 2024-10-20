package org.example.contract.request.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.Money;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateReturnedMaterialRequest {
    private Long id;
    private Long gasStationId;
    private Long materialId;
    private Long amount;
    private Money price;
    private Money centerPrice;
    private String status;
    private Long buyerId;
    private String invoiceNo;
}
