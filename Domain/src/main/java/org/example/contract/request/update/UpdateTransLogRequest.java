package org.example.contract.request.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.Money;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTransLogRequest {
    private Long id;
    private Long vehicleId;
    private Long transLineId;
    private Money fees;
    private Long transportationId;
    private String notes;
}
