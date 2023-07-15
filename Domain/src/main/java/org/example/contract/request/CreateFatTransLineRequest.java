package org.example.contract.request;

import lombok.Data;
import org.example.model.Place;

@Data
public class CreateFatTransLineRequest {
    private Place source;
    private Place destination;
    private String feeCurrency;
    private Double feeAmount;
}
