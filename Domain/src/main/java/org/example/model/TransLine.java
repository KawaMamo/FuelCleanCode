package org.example.model;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class TransLine {
    private Long id;
    private Place source;
    private Place destination;
    private Money fee;
    private LocalDateTime createdAt;
}
