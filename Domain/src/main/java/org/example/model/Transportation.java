package org.example.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class Transportation {

    private Long id;
    private Vehicle vehicle;
    private Refinery refinery;
    private Boolean isDivided;
    private Boolean isPriced;
    private Long size;

    private LocalDateTime createdAt;
    private List<Partition> partitions;
    private TransportationType type;
    private Document document;
    private LocalDateTime deletedAt;

}
