package org.example.model;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Category {

    private Long id;
    private PriceCategory priceCategory;
    private Material material;
    private Money price;
    private LocalDateTime createdAt;
}
