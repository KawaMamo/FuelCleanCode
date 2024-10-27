package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class OfficePayment extends Payment {
    @OneToOne
    private OfficeEntity office;
}
