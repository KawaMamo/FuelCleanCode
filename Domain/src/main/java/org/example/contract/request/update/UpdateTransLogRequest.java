package org.example.contract.request.update;

import lombok.Data;
import org.example.model.Money;
@Data
public class UpdateTransLogRequest {
    private Long id;
    private Long vehicleId;
    private Long transLineId;
    private Money fees;
    private Long transportationId;
    private String notes;
}
