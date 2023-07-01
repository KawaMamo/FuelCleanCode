package org.example.repositories.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String name;
    private String father;
    private String mother;
    private String nationalId;
    private String birthPlace;
    private LocalDate birthDate;
    private Boolean isActive;
    private LocalDateTime createdAt;
}
