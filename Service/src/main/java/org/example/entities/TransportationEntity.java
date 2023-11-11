package org.example.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.model.TransLog;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    private VehicleEntity vehicle;
    @OneToOne
    private RefineryEntity refinery;
    private Boolean isDivided;
    private Boolean isPriced;
    private Long size;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @Enumerated
    private TransportationType type;
    @OneToOne
    private DocumentEntity document;
    private LocalDateTime deletedAt;
    @OneToMany(mappedBy="transportationEntity")
    @ToString.Exclude
    private Set<PartitionEntity> partitionEntities;

    @OneToMany(mappedBy="transportation")
    @ToString.Exclude
    private Set<TransLogEntity> transLogs;

}
