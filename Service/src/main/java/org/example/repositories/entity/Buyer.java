package org.example.repositories.entity;

import jakarta.persistence.*;

@Entity
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String name;
    private String organization;
}
