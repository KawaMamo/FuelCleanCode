package org.example.contract.request.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.contract.request.Request;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateVehicleRequest implements Request {
    private String plateNumber;
    private Long trafficCenter_id;
    private Integer size;
    private Long office_id;
    private Long driver_id;
}
