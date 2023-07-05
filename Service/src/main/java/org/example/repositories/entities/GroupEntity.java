package org.example.repositories.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private LocalDateTime createdAt;
}
