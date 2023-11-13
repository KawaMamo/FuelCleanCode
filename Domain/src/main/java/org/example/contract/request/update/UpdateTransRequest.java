package org.example.contract.request.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.TransportationType;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTransRequest {
    private Long id;
    private Long refinery_id;
    private Long vehicle_id;
    private Long size;
    private TransportationType type;
}
