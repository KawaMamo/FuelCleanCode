package org.example.contract.request.update;

import lombok.Data;

@Data
public class UpdateGasStationRequest {
    private Long id;
    private String name;
    private Long priceCategoryId;
    private Long debtLimit;
    private Long regionId;
    private Long ownerId;
    private Long groupId;
}
