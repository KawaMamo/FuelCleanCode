package org.example.repositories.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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
    @OneToOne
    private Vehicle vehicle;
    @OneToOne
    private RefineryEntity refinery;
    private Boolean isDivided;
    private Boolean isPriced;
    private Long size;
    private LocalDateTime createdAt;
    @OneToMany
    private List<PartitionEntity> partition;
    @Enumerated
    private TransportationType type;
    @OneToOne
    private Document document;
    private LocalDateTime deletedAt;

}
