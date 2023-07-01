package org.example.repositories.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
public class TransLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @OneToOne
    private Place source;
    @OneToOne
    private Place destination;
    @OneToOne
    private Money fee;
    private LocalDateTime createdAt;

}
