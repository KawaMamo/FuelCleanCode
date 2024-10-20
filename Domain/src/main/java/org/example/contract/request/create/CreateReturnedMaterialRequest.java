package org.example.contract.request.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.contract.request.Request;
import org.example.model.Money;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReturnedMaterialRequest implements Request {
    private Long gasStationId;
    private Long materialId;
    private Long amount;
    private Money price;
    private Money centerPrice;
    private String status;
    private Long buyerId;
    private String invoiceNo;
}
