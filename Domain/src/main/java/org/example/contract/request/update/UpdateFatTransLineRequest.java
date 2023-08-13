package org.example.contract.request.update;

import lombok.Data;
import org.example.model.Place;
@Data
public class UpdateFatTransLineRequest {
    private Long id;
    private Place source;
    private Place destination;
    private String feeCurrency;
    private Double feeAmount;
}
