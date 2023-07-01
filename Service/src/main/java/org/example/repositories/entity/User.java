package org.example.repositories.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @OneToOne
    private PersonEntity personEntity;
    private AccountType accountType;
    @OneToOne
    private Document digitalSignature;
    private String branch;
    private Boolean isActivated;
    private byte[] fingerprint;
    private LocalDateTime createdAt;
}
