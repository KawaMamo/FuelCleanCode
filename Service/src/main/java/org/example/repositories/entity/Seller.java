package org.example.repositories.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String name;
}
