package org.example.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class Place {
    private Long id;
    private String placeType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
