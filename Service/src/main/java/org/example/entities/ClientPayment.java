package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class ClientPayment extends Payment {
    @OneToOne
    private GasStationEntity gasStation;
}
