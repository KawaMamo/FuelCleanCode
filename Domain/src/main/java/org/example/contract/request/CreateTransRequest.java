package org.example.contract.request;

import lombok.Data;
import org.example.model.Refinery;
import org.example.model.TransportationType;
import org.example.model.Vehicle;
@Data
public class CreateTransRequest {

    private Long refinery_id;
    private Long vehicle_id;
    private Long size;
    private TransportationType type;

}
