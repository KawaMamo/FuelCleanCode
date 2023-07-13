package org.example.contract.request;

import lombok.Data;
import org.example.model.*;

@Data
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
