package org.example.model;

import lombok.Data;

@Data
public class SellerPayment extends Payment{
    private Seller seller;
}
