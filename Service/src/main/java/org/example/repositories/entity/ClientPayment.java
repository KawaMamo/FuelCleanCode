package org.example.repositories.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class ClientPayment extends Payment {
    @OneToOne
    private GasStation gasStation;
}
