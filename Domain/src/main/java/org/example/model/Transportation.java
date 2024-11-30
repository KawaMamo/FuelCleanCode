package org.example.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class Transportation extends TransportationReason{


    private Refinery refinery;
    private Boolean isDivided;
    private Boolean isPriced;
    private Long size;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Partition> partitions;
    private List<TransLog> transLogs;
    private List<Document> document;
    private LocalDateTime deletedAt;
    private Vehicle vehicle;

}
