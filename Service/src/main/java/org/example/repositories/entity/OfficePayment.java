package org.example.repositories.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;


@Data
@Entity
public class OfficePayment extends Payment {
    @OneToOne
    private OfficeEntity officeEntity;
}
