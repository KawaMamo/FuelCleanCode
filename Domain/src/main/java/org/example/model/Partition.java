package org.example.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Partition {

    private Long Id;
    private Material material;
    private Transportation transportation;
    private Integer amount;
    private Integer correctedAmount;
    private Money price;
    private GasStation gasStation;
    private String notes;
    private String extraNotes;
    private Document document;
    private LocalDateTime createdAt;

}
