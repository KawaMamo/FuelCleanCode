package org.example.repositories.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class ClientPayment extends Payment {
    @OneToOne
    private GasStation gasStation;
}
