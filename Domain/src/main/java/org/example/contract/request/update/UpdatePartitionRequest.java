package org.example.contract.request.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.Money;
@Data
@AllArgsConstructor
@NoArgsConstructor
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
