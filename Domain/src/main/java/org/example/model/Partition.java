package org.example.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Partition {

    private Long id;
    private Material material;
    private Integer amount;
    private Integer correctedAmount;
    private Money price;
    private GasStation gasStation;
    private String notes;
    private String extraNotes;
    private Document document;
    private LocalDateTime createdAt;

}
