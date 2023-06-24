package org.example.model;

import lombok.Data;

@Data
public class ClientPayment extends Payment{
    private GasStation gasStation;
}
