package org.example.model;

import java.time.LocalDateTime;

public class Expense {
    private Long id;
    private String details;
    private Money amount;
    private String paidTo;
    private LocalDateTime createdAt;
}
