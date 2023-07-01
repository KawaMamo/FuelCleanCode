package org.example.repositories.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Money {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String currency;
    private Double amount;
}
