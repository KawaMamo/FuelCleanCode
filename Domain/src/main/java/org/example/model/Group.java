package org.example.model;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Group {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
}
