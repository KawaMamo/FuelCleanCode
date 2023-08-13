package org.example.contract.request.update;

import lombok.Data;
import org.example.model.Money;
@Data
public class UpdatePartitionRequest {
    private Long id;
    private Long materialId;
    private Integer amount;
    private Integer correctedAmount;
    private Money price;
    private Long gasStationId;
    private String notes;
    private String extraNotes;
    private Long transportationId;
}
