package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransLog {
    private Long id;
    private Vehicle vehicle;
    private TransLine transLine;
    private Money fees;
    private TransportationReason transportation;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
