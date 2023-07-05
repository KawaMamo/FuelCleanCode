package org.example.model;

import lombok.Data;

@Data
public class Money {
    private Long id;
    private String currency;
    private Double amount;
}
