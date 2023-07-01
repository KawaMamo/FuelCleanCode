package org.example.repositories.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
public class TransLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @OneToOne
    private Vehicle vehicle;
    @OneToOne
    private TransLine transLine;
    @OneToOne
    private Money fees;
    @OneToOne
    private TransportationEntity transportationEntity;
    private String notes;
    private LocalDateTime createdAt;
}
