package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class ReturnedMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToOne
    private GasStationEntity gasStation;
    @OneToOne
    private MaterialEntity material;
    private Long amount;
    private String priceCurrency;
    private Double priceAmount;
    private String centerPriceCurrency;
    private Double centerPriceAmount;
    private String status;
    @OneToOne
    private Buyer buyer;
    private String invoiceNo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
