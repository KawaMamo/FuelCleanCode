package org.example.contract.request.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.Money;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateClientPaymentRequest {

    private Money amount;
    private Long billNumber;
    private String notes;
    private Long gasStationId;
}
