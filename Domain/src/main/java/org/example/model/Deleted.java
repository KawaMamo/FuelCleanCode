package org.example.model;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Deleted {
    private Long id;
    private String jsonValue;
    private LocalDateTime createdAt;
    private String userId;
}
