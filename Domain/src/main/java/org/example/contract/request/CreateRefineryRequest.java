package org.example.contract.request;

import lombok.Data;

@Data
public class CreateRefineryRequest implements Request{
    private String name;
}
