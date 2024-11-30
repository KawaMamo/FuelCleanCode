package org.example.model;

import lombok.Data;

@Data
public abstract class TransportationReason {
    private Long id;
    private TransportationType type;
}
