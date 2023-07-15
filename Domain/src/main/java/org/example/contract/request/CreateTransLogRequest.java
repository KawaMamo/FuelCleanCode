package org.example.contract.request;

import lombok.Data;
import org.example.model.Money;

@Data
public class CreateTransLogRequest {
    private Long vehicleId;
    private Long transLineId;
    private Money fees;
    private Long transportationId;
    private String notes;
}
