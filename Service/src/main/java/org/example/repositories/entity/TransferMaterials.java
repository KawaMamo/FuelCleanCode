package org.example.repositories.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
public class TransferMaterials {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @OneToOne
    private GasStation source;
    @OneToOne
    private GasStation destination;
    @OneToOne
    private Material material;
    private Long amount;
    @OneToOne
    private Money price;
    private LocalDateTime createdAt;
}
