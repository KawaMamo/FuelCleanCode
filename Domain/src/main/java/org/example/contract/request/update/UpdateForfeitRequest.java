package org.example.contract.request.update;

import lombok.Data;
import org.example.model.Money;
@Data
public class UpdateForfeitRequest {

    private Long id;
    private Long vehiclesId;
    private Long partitionId;
    private Money price;
    private String reason;
}
