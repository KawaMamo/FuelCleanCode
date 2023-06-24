package org.example.model;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class WareHouse {
    private Long id;
    private Long name;
    LocalDateTime createdAt;
}
