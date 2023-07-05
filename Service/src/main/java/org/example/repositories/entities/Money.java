package org.example.repositories.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Money {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String currency;
    private Double amount;
}
