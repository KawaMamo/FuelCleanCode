package org.example.contract.request.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.contract.request.Request;
import org.example.model.Refinery;
import org.example.model.TransportationType;
import org.example.model.Vehicle;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransRequest implements Request {

    private Long refinery_id;
    private Long vehicle_id;
    private Long size;
    private TransportationType type;
    private Boolean isPriced;
    private LocalDateTime createdAt;

}
