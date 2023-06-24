package org.example.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class Transportation {
    protected Long id;
    protected Vehicle vehicle;
    protected Boolean isDivided;
    protected Boolean isPriced;
    protected Long size;
    protected LocalDateTime createdAt;
    protected List<Partition> partitions;
    protected String type;
    protected Document document;
    protected LocalDateTime deletedAt;

}
