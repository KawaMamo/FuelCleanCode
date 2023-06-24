package org.example.model;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Income {
    private Long id;
    private Material material;
    private Long amount;
    private Long correctedAmount;
    private WareHouse wareHouse;
    private String notes;
    private LocalDateTime createdAt;

}
