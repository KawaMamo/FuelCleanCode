package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PlaceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String placeType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @Column(unique = true)
    private String name;
}
