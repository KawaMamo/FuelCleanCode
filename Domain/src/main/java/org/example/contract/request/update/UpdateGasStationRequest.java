package org.example.contract.request.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateGasStationRequest {
    private Long id;
    private String name;
    private String translation;
    private Long priceCategoryId;
    private Long debtLimit;
    private Long regionId;
    private Long ownerId;
    private Long groupId;
}
