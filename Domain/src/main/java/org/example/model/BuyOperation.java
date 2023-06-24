package org.example.model;

import lombok.Data;

@Data
public class BuyOperation extends Transportation{

    private String source;
    private Money price;

}
