package org.example.contract.request.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.Money;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateForfeitRequest {
    private Long vehiclesId;
    private Long partitionId;
    private Money price;
    private String reason;
}
