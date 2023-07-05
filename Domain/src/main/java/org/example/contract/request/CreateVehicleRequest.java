package org.example.contract.request;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CreateVehicleRequest implements Request{
    private String plateNumber;
    private Long trafficCenter_id;
    private Integer size;
    private Long office_id;
    private Long driver_id;
}
