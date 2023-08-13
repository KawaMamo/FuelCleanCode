package org.example.contract.request.create;

import lombok.Data;
@Data
public class CreateGasStationRequest {
    private String name;
    private Long priceCategoryId;
    private Long debtLimit;
    private Long regionId;
    private Long ownerId;
    private Long groupId;
}
