package org.example.repositories.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
}
