package org.example.contract.request.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.Money;
import org.example.model.TransportationReason;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FatCreateTransLogRequest {
    private Long vehicleId;
    private Long transLineId;
    private Money fees;
    private TransportationReason transportation;
    private String notes;
}
