package org.example.contract.request.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePartitionRequest {
    private Long materialId;
    private Integer amount;
    private Integer correctedAmount;
    private Money price;
    private Long gasStationId;
    private String notes;
    private String extraNotes;
    private Long transportationId;
}
