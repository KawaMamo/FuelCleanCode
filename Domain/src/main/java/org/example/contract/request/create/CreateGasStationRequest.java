package org.example.contract.request.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateGasStationRequest {
    private String name;
    private String translation;
    private Long priceCategoryId;
    private Long debtLimit;
    private Long regionId;
    private Long ownerId;
    private Long groupId;
    private Long materialId;
}
