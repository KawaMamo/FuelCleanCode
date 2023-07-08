package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class SellerPayment extends Payment {
    @OneToOne
    private Seller seller;
}
