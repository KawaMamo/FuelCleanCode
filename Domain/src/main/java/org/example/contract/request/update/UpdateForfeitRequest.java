package org.example.contract.request.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.Money;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateForfeitRequest {

    private Long id;
    private Long vehiclesId;
    private Long partitionId;
    private Money price;
    private String reason;
}
