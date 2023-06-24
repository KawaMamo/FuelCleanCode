package org.example.model;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class TransLog {
    private Long Id;
    private Vehicle vehicle;
    private TransLine transLine;
    private Money fees;
    private Transportation transportation;
    private String notes;
    private LocalDateTime createdAt;
}
