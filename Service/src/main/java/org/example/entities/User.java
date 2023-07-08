package org.example.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToOne
    private PersonEntity person;
    private AccountType accountType;
    @OneToOne
    private Document digitalSignature;
    private String branch;
    private Boolean isActivated;
    private byte[] fingerprint;
    private LocalDateTime createdAt;
}
