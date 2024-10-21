package org.example.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Buyer {
    private Long id;
    private String name;
    private String organization;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
