package org.example.model;

import lombok.Data;

@Data
public class BuyerPayment extends Payment{
    private Buyer buyer;
}
