package org.example.repositories.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class GasStation extends Place {

    protected String name;
    protected LocalDateTime createdAt;
    @OneToOne
    private PriceCategory priceCategory;
    private Long debtLimit;
    @OneToOne
    private Region region;
    @OneToOne
    private PersonEntity owner;
    @OneToOne
    private GroupEntity groupEntity;
}
