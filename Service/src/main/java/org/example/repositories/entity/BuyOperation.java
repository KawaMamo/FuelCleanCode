package org.example.repositories.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class BuyOperation extends TransportationEntity {

    private String source;
    @OneToOne
    private Money price;

}
