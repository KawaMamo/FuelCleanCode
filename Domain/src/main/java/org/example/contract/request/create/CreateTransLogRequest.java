package org.example.contract.request.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.Money;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransLogRequest {
    private Long vehicleId;
    private Long transLineId;
    private Money fees;
    private Long transportationReasonId;
    private String notes;
}
