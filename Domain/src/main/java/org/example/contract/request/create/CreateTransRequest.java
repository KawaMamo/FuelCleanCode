package org.example.contract.request.create;

import lombok.Data;
import org.example.contract.request.Request;
import org.example.model.Refinery;
import org.example.model.TransportationType;
import org.example.model.Vehicle;
@Data
public class CreateTransRequest implements Request {

    private Long refinery_id;
    private Long vehicle_id;
    private Long size;
    private TransportationType type;

}
