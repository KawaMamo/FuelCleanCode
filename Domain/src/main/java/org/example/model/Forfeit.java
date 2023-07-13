package org.example.model;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Forfeit {
    private Long id;
    private Vehicle vehicles;
    private Partition partition;
    private Money price;
    private String reason;
    private LocalDateTime createdAt;
}
