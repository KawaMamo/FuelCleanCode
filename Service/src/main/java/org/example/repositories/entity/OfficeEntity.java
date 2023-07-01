package org.example.repositories.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
public class OfficeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String name;
    private LocalDateTime createdAt;

}
