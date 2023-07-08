package org.example.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class TransportationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private Long vehicleEntityId;
    private Long refineryId;
    private Boolean isDivided;
    private Boolean isPriced;
    private Long size;
    private LocalDateTime createdAt;
    @Enumerated
    private TransportationType type;
    private Long documentId;
    private LocalDateTime deletedAt;

}
