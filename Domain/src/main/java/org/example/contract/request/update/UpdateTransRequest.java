package org.example.contract.request.update;

import lombok.Data;
import org.example.model.TransportationType;
@Data
public class UpdateTransRequest {
    private Long id;
    private Long refinery_id;
    private Long vehicle_id;
    private Long size;
    private TransportationType type;
}
