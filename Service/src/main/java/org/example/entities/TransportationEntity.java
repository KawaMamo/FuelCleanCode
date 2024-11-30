package org.example.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class TransportationEntity extends TransportationReasonEntity {

    @OneToOne
    private VehicleEntity vehicle;
    @OneToOne
    private RefineryEntity refinery;
    private Boolean isDivided;
    private Boolean isPriced;
    private Long size;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @OneToMany
    private List<DocumentEntity> document;
    private LocalDateTime deletedAt;
    @OneToMany(mappedBy="transportationEntity")
    @ToString.Exclude
    private Set<PartitionEntity> partitionEntities;

    @OneToMany(mappedBy="transportation")
    @ToString.Exclude
    private Set<TransLogEntity> transLogs;

}
