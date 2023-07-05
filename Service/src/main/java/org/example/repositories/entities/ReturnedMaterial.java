package org.example.repositories.entities;

import jakarta.persistence.*;

@Entity
public class ReturnedMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToOne
    private GasStation gasStation;
    @OneToOne
    private Material material;
    private Long amount;
    private String priceCurrency;
    private Double priceAmount;
    private String centerPriceCurrency;
    private Double centerPriceAmount;
    private String status;
    @OneToOne
    private Buyer buyer;
    private String invoiceNo;
}
