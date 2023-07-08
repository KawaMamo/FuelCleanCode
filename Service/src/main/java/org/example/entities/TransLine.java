package org.example.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
public class TransLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToOne
    private Place source;
    @OneToOne
    private Place destination;
    private String feeCurrency;
    private Double feeAmount;
    private LocalDateTime createdAt;

}
