package org.example.repositories.entity;

import jakarta.persistence.*;

@Entity
public class ReturnedMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @OneToOne
    private GasStation gasStation;
    @OneToOne
    private Material material;
    private Long amount;
    @OneToOne
    private Money price;
    @OneToOne
    private Money centerPrice;
    private String status;
    @OneToOne
    private Buyer buyer;
    private String invoiceNo;
}
