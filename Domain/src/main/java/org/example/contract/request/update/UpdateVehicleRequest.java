package org.example.contract.request.update;

import lombok.Data;

@Data
public class UpdateVehicleRequest {
    private Long id;
    private String plateNumber;
    private Long trafficCenter_id;
    private Integer size;
    private Long office_id;
    private Long driver_id;
}
