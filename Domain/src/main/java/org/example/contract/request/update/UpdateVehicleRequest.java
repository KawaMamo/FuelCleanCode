package org.example.contract.request.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateVehicleRequest {
    private Long id;
    private String plateNumber;
    private Long trafficCenter_id;
    private Integer size;
    private Long office_id;
    private Long driver_id;
}
